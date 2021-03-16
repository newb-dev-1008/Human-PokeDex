import numpy as np

imagePaths = # Path of folder containing new images

for imagePath in imagePaths:
    name = imagePath.split(os.path.sep)[-2]

    image = cv2.imread(imagePath)
    blurredImage = gaussianBlur(copy.deepcopy(image))
    filename = "blurred"
    cv2.imwrite("")

# Function to blur the image
def blurImage(image):
    blurredImage = gaussianBlur(copy.deepcopy(image))
    filename = "blurred_" + imagePath.split(os.path.sep)[-1] + ".jpg"
    cv2.imwrite(filename, blurredImage))

# Function to sharpen the image
