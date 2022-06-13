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
1. Dataset ingestion (from google images)
2. Feature exploration
3. Using transfer learning DenseNet201
4. Reduce Prameter and tuning the layer selected
5. Test Prediction
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
2. Create a API for authentication on Google Cloud Identity
   -> With 2 Sign-in Providers:
        - Email/Password
        - Google
3. Create a project on Firebase for Android
   -> Get google-services.json to integrated with Android Studio
4. Create a firestore for the database with collection food, user, etc.
5. Create storage with records and profile folders
    - Cloud Storage Browser page
    - Create bucket
    - Name your bucket : "-----"
    - Location type : region
    - Choose where to store your data = asia-southeast2
    - Leave the default setting
    - Create
6. Create Rest Api with cloud functions
   -> Get Functions Url and connect to database 
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
- [Food Image Classification with Convolutional Neural Networks](https://cs230.stanford.edu/projects_fall_2019/reports/26233496.pdf)

### Design Apps :
[Design](https://www.figma.com/file/jlg0mPLIWr7AnjQLbTlDD5/Desain-MencaK?node-id=0%3A1)
