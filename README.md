<h1 align="center">
Bangkit Team C22-PS076
</h1>
<p align="center">
Capstone for Bangkit 2021
</p>
<p align="center">
<img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-4.3.1-blue?logo=kotlin">
<img alt="Android Studio" src="https://img.shields.io/badge/Android%20Studio-4.1.2-green?logo=android-studio">
<img alt="Android" src="https://img.shields.io/badge/Android-3DDC84?logo=android&logoColor=white">
<img alt="TensorFlow" src="https://badges.aleen42.com/src/tensorflow.svg">
<img alt="Firebase" src="https://img.shields.io/badge/firebase-%23039BE5.svg?logo=firebase"/>
</p>

# Member
1. Mufti Restu Mahesa (A2189F1803) - Universitas Bengkulu
2. Adde Nanda Caesario Putra (A7189F1806) - Universitas Bengkulu
3. Muhammad Daffa Alfajri (M7189F1805) - Universitas Bengkulu
4. Maria Misela Adelheid Wona (M7211F1962) - Universitas Indraprasta PGRI
5. Khoirus Fauzi Rahmadhani (C2004G0236) - Institut Teknologi Sepuluh Nopember
6. Muhammad Aditya Muharram (C2322F2825) - Universitas Syiah Kuala

## Theme : Tourism, Creative, and Digital Economy
### Title of Project : MencaK ( Mencari Kuliner )

### Summary of Project
our team will create an application called Mencak (Searching for Culinary). The function of this application is to solve all the Indonesian culinary problems that we described on capstone project plan such as a documentation etc. With MencaK, users can find local culinary available in an area, detect the name of the food along with the origin and description of the food. Apart from that, this app provides food-only social media that users can use to post everything about food and share with other users and also can scan what is the food using Machine Learning.

## Steps to replicate this project
### Machine Learning
1. Dataset ingestion (from Kaggle)
2. Feature exploration
3. Preprocessing (binary encoding, dividing data, check numbers of data, and scaling the data to prepare for the ML training)
4. Define deep learning model using TensorFlow (use 2 dense layers)
5. Hyperparameter tuning with the help of GridSearchCV from scikit-learn library and train the model
6. Save and load model to evaluate model performance

### Mobile Development
1. Design UI layout (optional: Figma)
2. Dependencies (see Technology used part)
3. Navigation
4. Implement external feature (accessing camera and gallery)
5. Connecting to remote (using Firestore for database and Firebase storage for file)
6. Implement machine learning using TFLite

### Cloud Computing
1. Create a project on Google Cloud Platform
2. Set default region as asia-southeast2(Jakarta)
    > go to gcp console and write this command : $gcloud config set compute/region asia-southeast2
3. Create a project on Firebase
4. Create storage with records and profile folders
    - Cloud Storage Browser page
    - Create bucket
    - Name your bucket : "-----"
    - Location type : region
    - Choose where to store your data = asia-southeast2
    - Leave the default setting
    - Create
5. Create a firestore for the database with collection note, patient, record, staff
6. Input machine learning model in Firebase

## Technology used

- [Coroutine Flow](https://developer.android.com/kotlin/flow)
- [Firebase](https://firebase.google.com)
- [TensorFlow](https://www.tensorflow.org/lite/guide/android)
- [Glide](https://github.com/bumptech/glide)
- [Lottie](https://github.com/airbnb/lottie-android)
- [Data2viz](https://github.com/data2viz/data2viz)
- [Google Cloud Platform](https://cloud.google.com/gcp)

## Project Resources
### Budget
Google Cloud Platform Subscription : **$200**

### Dataset:
- https://drive.google.com/drive/folders/1S6SKK3th0OGKFRIE0xgj6hSqvbLjfJLN?usp=sharing

### Paper / Journals / articles:
- [Development of a Mobile Personal Health Record Application Designed for Emergency Care in Korea; Integrated Information from Multicenter Electronic Medical Records](https://www.mdpi.com/2076-3417/10/19/6711/pdf)

### Design Apps :
[Design](https://www.figma.com/file/jlg0mPLIWr7AnjQLbTlDD5/Desain-MencaK?node-id=0%3A1)
