import numpy as np
import Augmentor

imagePaths = # Path of folder containing new images

for imagePath in imagePaths:
    name = imagePath.split(os.path.sep)[-2]

    image = cv2.imread(imagePath)
    blurredImage = gaussianBlur(copy.deepcopy(image))
    filename = "blurred"
    cv2.imwrite("")


# ---------------------------- Functions for data augmentation ----------------------------

# Function to blur the image
def blurImage(image):
    blurredImage = gaussianBlur(copy.deepcopy(image))
    filename = "blurred_" + imagePath.split(os.path.sep)[-1] + ".jpg"
    cv2.imwrite(filename, blurredImage))

# Function to sharpen the image
def sharpenImage(image):
    kernel = np.array([[-1, -1, -1], [-1, 9, -1], [-1, -1, -1]])
    sharpImage = cv2.filter2D(image, -1, kernel)
    filename = "sharpened_" + imagePath.split(os.path.sep)[-1] + ".jpg"
    cv2.imwrite(filename, sharpImage)

# Function to add Sepia effect 
def sepiaImage(image):
    kernel = np.array([[0.272, 0.534, 0.131], [0.349, 0.686, 0.168], [0.393, 0.769, 0.189]])
    sepiaImage = cv2.filter2D(image, -1, kernel)
    filename = "sepia_" + imagePath.split(os.path.sep)[-1] + ".jpg"
    cv2.imwrite(filename, sharpImage)

# Function to add brightness
def brightImage(image):
    brightImage = cv2.convertScaleAbs(image, 3)
    filename = "bright_" + imagePath.split(os.path.sep)[-1] + ".jpg"
    cv2.imwrite(filename, brightImage)

# Tilt image to certain angles
def tiltedImage(image, imagePath):
    p = Augmentor.Pipeline(imagePath)
    p.rotate(probability = 1, max_left_rotate = 15, max_right_rotate = 15)
    tilted_images, label = p.sample(10)
    for i in range(len(tilted_images)):
        filename = "tilted_" + imagePath.split(os.path.sep)[-1] + "_" + i + ".jpg"
        cv2.imwrite(filename, tilted_images[i])