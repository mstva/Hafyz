import os
import flask
import werkzeug
import face_recognition
import json

def processData(testImage):
    # create empty lists to save person names and face encodings
    known_face_encodings = []
    known_face_names = []

    # get the names from names txt fils and add them to the list
    names = []
    names_file = open("images/data/names.txt", "r")
    for name in names_file:
        names.append(name.strip())

    # get train images names from txt image names and add them to the list
    trainImagesNames = []
    images_names = open("images/data/train_image_names.txt", "r")
    for img_name in images_names:
        trainImagesNames.append(img_name.strip())

    # loop through names and train image names and create face endcodings
    for name, trainImageName in zip(names, trainImagesNames):
        # get the face encoding for each image
        train_image_path = "images/train/" + trainImageName
        image_to_train = face_recognition.load_image_file(train_image_path)
        train_face_encodings = face_recognition.face_encodings(image_to_train)[0]
        # add names and encodings to thier lists
        known_face_names.append(name)
        known_face_encodings.append(train_face_encodings)

    # create face encoding and face locations for test image
    image_to_test = face_recognition.load_image_file(testImage)
    face_locations = face_recognition.face_locations(image_to_test)
    test_face_encodings = face_recognition.face_encodings(image_to_test, face_locations)

    # loop through face encoding and locations to find any matched faces
    for (top, right, bottom, left), face_encoding in zip(face_locations, test_face_encodings):

        # compare to find the matches in test image
        matches = face_recognition.compare_faces(known_face_encodings, face_encoding)

        if True in matches:
            first_match_index = matches.index(True)
            name = known_face_names[first_match_index]
            return {'name': name, 'top': top, 'right': right, 'bottom': bottom, 'left': left}

        if False  in matches:
            name = "Unknown Person"
            return {'name': name, 'top': top, 'right': right, 'bottom': bottom, 'left': left}


def startServer():

    app = flask.Flask(__name__)

    @app.route('/train', methods=['GET', 'POST'])
    def getTrainData():

        # get image and image name
        imageFile = flask.request.files['train_image']
        filename = werkzeug.utils.secure_filename(imageFile.filename)

        # save the image
        train_image_path = "images/train/" + filename
        imageFile.save(train_image_path)

        # add image name to txt file to store train image names
        train_images_names = open("images/data/train_image_names.txt", "a")
        train_images_names.write(filename)
        train_images_names.write("\n")

        # get the person name
        name = flask.request.form['person_name']

        # add the person name to txt file to store names
        names = open("images/data/names.txt", "a")
        names.write(name)
        names.write("\n")

        return "Train Image Uploaded Successfully"

    @app.route('/test', methods=['GET', 'POST'])
    def getTestData():

        # get the test image drom android
        imageFile = flask.request.files['test_image']
        filename = werkzeug.utils.secure_filename(imageFile.filename)

        # save the test image in test folder
        imageFile.save("images/test/" + filename)

        # create path for test image uploaded
        test_image_path = 'images/test/' + filename

        # pass this path to process test image and rocognize face
        data_dict = processData(test_image_path)

        # convert the dict returend by processing data to  json
        json_data = json.dumps(data_dict)

        # remove the test image from the folder
        os.remove('images/test/' + filename)

        return json_data

    app.run(host="0.0.0.0", port=5000, debug=True)

def main():
    startServer()

if __name__ == '__main__':
    main()