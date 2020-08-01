import flask
import werkzeug
import os

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

    app.run(host="0.0.0.0", port=5000, debug=True)

def main():
    startServer()

if __name__ == '__main__':
    main()