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
    
# Mirror image
def mirrorImage(image, imagePath):
    p = Augmentor.Pipeline(imagePath)
    p.flip_left_right(probability = 1)
    flipped_images, label = p.sample(1)
    filename = "mirror_" + imagePath.split(os.path.sep)[-1] + ".jpg"
    cv2.imwrite(filename, flipped_images[0])

# Dull the image

# Shearing image
def shearImage(image, imagePath):
    p = Augmentor.Pipeline(imagePath)
    p.shear(probability = 1, 15, 15)
    sheared_images, label = p.sample(10)
    for i in range(len(sheared_images)):
        filename = "sheared_" + imagePath.split(os.path.sep)[-1] + "_" + i + ".jpg"
        cv2.imwrite(filename, sheared_images[i])

# Skewing image
def skewedImage(image, imagePath):
    p = Augmentor.Pipeline(imagePath)
    p.skew(probability = 1, magnitude = 0.7)
    skewed_images, label = p.sample(10)
    for i in range(len(skewed_images)):
        filename = "skewed_" + imagePath.split(os.path.sep)[-1] + "_" + i + ".jpg"
        cv2.imwrite(filename, skewed_images[i])

# Black and White 
def bwImage(image, imagePath):
    p = Augmentor.Pipeline(imagePath)
    p.black_and_white(probability = 1, threshold = 256)
    bwImages, label = p.sample(1)
    for i in range(len(bwImages)):
        filename = "bw_" + imagePath.split(os.path.sep)[-1] + ".jpg"
        cv2.imwrite(filename, bwImages[0])