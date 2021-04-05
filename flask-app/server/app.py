from flask import Flask
import os
from pythonfiles import augment_data
from pythonfiles import extract_embeddings

app = Flask(__name__)
@app.route('/')

def index():
    # Complete this
    augment_data.mainAugment()
    

if __name__ == '__main__':
    app.run(debug = True, host = '0.0.0.0', port = int(os.environ.get('PORT', 8080)))