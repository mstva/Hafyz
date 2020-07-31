import flask

def startServer():

    app = flask.Flask(__name__)

    app.run(host="0.0.0.0", port=5000, debug=True)

def main():
    startServer()

if __name__ == '__main__':
    main()