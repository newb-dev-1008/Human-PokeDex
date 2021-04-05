from flask import Flask
import os
from pythonfiles.augment_data import *
from pythonfiles.extract_embeddings import *

app = Flask(__name__)
@app.route('/')

def index():
    # Complete this 


if __name__ == '__main__':
    app.run(debug = True, host = '0.0.0.0', port = int(os.environ.get('PORT', 8080)))