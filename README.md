# Notes-App
Offline Notes App - built using MVVM, Room DB, Live Data, Data Binding, having Unit Tests

<div align="center">
  <a href="https://drive.google.com/file/d/1_DAKmWLr0CmwRzIVbx50k-u4fe2au5mC/view?usp=sharing">
    <img src="https://user-images.githubusercontent.com/62820147/172266249-abe342b6-1b0f-41b6-9889-d86a2d9e115d.png" alt="Logo" width="80" height="80">
  </a>
</div>




<!-- ABOUT THE PROJECT -->
## About The Project


</br>
<p float="left">
<img src="https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiD3t73exLBGYFHPAb92Xm06YaL6XtqN8GWm72UnNjtSsF3K7-81Lu0beU3QJyECnUq8WRoyfQ1BWrcCOwTSZ75IAnOwf5lSQpZoeBiC0AB4a1oAS4BEpHyLHWaLnaHsNTfYUe4nHo6a_puCmgnQCKO_xlHURqyCjP1qdDE7dDr1YyNh9quNAVCXnGw7Q/s2562/WhatsApp%20Image%202022-06-07%20at%201.41.49%20AM_framed.png"  width="216" height="480" />
<img src="https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEjAZLLQYtFh3l4dOhBkmpcc0PelKXQuwb9JjhwsrtXTl1UTnIgJhPlo2PgE14bRHkLmR5air12oxlNpsk34Helu6O7ARheDPp2lrk_SywDj4-P9PuP_Vu1n-BBAebEzGqJNqb-oET3qX7qxgr2gO9pBJ0kMWFog2BY5XMVAy88NhGla3ngqLIP_2l95cw/s2562/WhatsApp%20Image%202022-06-07%20at%205.32.40%20AM_framed.png"  width="216" height="480" />
<img src="https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEg5UeCVJTcVg5uKRil8j5YuBd5qeMF3fjjnr6K2a38L1pJfYAyQ6cEApGrAaxOd3-ebyf5prczdhN29oTG2m_WjcBzWoHiMe7iewdxrNS-398tOEt_BS2VqUgyq-kr5snWGmg_XabgHR6TvMUDzBQjXzleZmHpLU3vxb7fcJIzqp3KLWx9S-rVdZjR5CQ/s2562/WhatsApp%20Image%202022-06-07%20at%205.32.40%20AM%20(1)_framed.png"  width="216" height="480" />
<img src="https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEjQH34buJZoYj9r_oX0G1F2hEvqslav97ML0Z2nme4KQoYT8f2VsbWzw73G2AJqul9Sg9xt3Zoc2dvuKeuRmeIos_DubWTdTVYmuGz58GZVjp51xCA77I0ahdXdjg-JRy23kT4f0364WEK7G7x5OrVITWcEInJ4W-dXB3r1rAykPVOOKEnREdEgQ_7i1w/s2562/WhatsApp%20Image%202022-06-07%20at%205.30.34%20AM_framed.png"  width="216" height="480" />
</p>

* Login screen - This screen asks for details- Mobile/Email, Password 
* Signup screen - This screen asks for details - Name, Mobile, Email, Password and saves password in encrypted form. 
* Home screen - This screen contains - List of all notes. The List row contain: Title, Description and photo. 
 * - one clicks on Row opens the details screen. 
* Details screen - This screen shows photos, title and description. 
* Add note screen - This screen asks for - Note Title (min 5 to max 100 characters), 
* Description (min 100 to max 1000 characters), Add photo (add from gallery or capture from camera (max 10 photos) 

<p align="right">(<a href="#top">back to top</a>)</p>

### Installation

_Below are steps for installing and setting up the app. 

1. Download the Apk from here [Click Here to DownLoad Apk](https://drive.google.com/file/d/1_DAKmWLr0CmwRzIVbx50k-u4fe2au5mC/view?usp=sharing)
2. Install and run it.

### Technologies Used:

This section list some major frameworks/libraries/tools used to build this app. 

* Android Studio
* Java
* Xml
* SQLite
* Room
* Threads/ Async
* Android Arch. Components
* Live data
* Data Binding
* Unit Testing

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Entity Relationship Diagram
<img src="https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEil38Jfe1yktdmnChByIRPDLglna0CSmzOgVnnP_9NSSV8sZEP9pLUOSnL6oXgrXKRB_KEpBToDIBDstJ4odUi-Scyb0tCeK6yyrS-qj6V5scAz0pDrsy4TxlS_W798Ok11ttAz_uoK7QB5l4u6QVGDfbYQAfpAsLVEe0x-F51y_NjrsR8U1FwAJQ0lSw/s1281/Screenshot%202022-06-07%20055531.jpg"   />



### Architecture Used

Used MVVM to develop the app and to accelerate development and make future maintenance much easier.
<img src="https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhM6GjenNlIHdIrrlSsM6wTfQgRGgGMPACVJQP-ONyJKjYGL_9OVgBy_OcQ5kdHPT0ODTpeWpaFSKAoZkiW3zytgMu4SmSQjI39T-Yki1ssA_r_KrJ0nLXiFK7Sly9D6pEDaEQnKTmauqJwKB55LfxGY3l3bKv7x4NRiAA7uUa1wGOFuWz9i-_Uvg92hA/s1152/Screenshot%202022-06-07%20060431.jpg"     />

**views:**
* [AddNoteActivity.java](https://github.com/Me-Abhishek-patel/Notes-App/blob/master/app/src/main/java/com/ciberciti/notes/ui/addnote/AddNoteActivity.java)
* [AuthActivity.java](https://github.com/Me-Abhishek-patel/Notes-App/blob/master/app/src/main/java/com/ciberciti/notes/ui/auth/AuthActivity.java)
* [LoginFragment.java](https://github.com/Me-Abhishek-patel/Notes-App/blob/master/app/src/main/java/com/ciberciti/notes/ui/auth/LoginFragment.java)
* [RegisterFragment.java](https://github.com/Me-Abhishek-patel/Notes-App/blob/master/app/src/main/java/com/ciberciti/notes/ui/auth/RegisterFragment.java)
* [NoteDetailsActivity.java](https://github.com/Me-Abhishek-patel/Notes-App/blob/master/app/src/main/java/com/ciberciti/notes/ui/notedetails/NoteDetailsActivity.java)
* [MainActivity.java](https://github.com/Me-Abhishek-patel/Notes-App/blob/master/app/src/main/java/com/ciberciti/notes/ui/MainActivity.java)

**view Models:**
* [AddNoteActivityViewModel.java](https://github.com/Me-Abhishek-patel/Notes-App/blob/master/app/src/main/java/com/ciberciti/notes/ui/addnote/AddNoteActivityViewModel.java)
* [AuthViewModel.java](https://github.com/Me-Abhishek-patel/Notes-App/blob/master/app/src/main/java/com/ciberciti/notes/ui/auth/AuthViewModel.java)
* [NotesDetailsActivityViewModel.java](https://github.com/Me-Abhishek-patel/Notes-App/blob/master/app/src/main/java/com/ciberciti/notes/ui/notedetails/NotesDetailsActivityViewModel.java)
* [MainActivityViewModel.java](https://github.com/Me-Abhishek-patel/Notes-App/blob/master/app/src/main/java/com/ciberciti/notes/ui/MainActivityViewModel.java)

**Repository**
* [NotesRepository.java](https://github.com/Me-Abhishek-patel/Notes-App/blob/master/app/src/main/java/com/ciberciti/notes/repository/NotesRepository.java)

**Entity**
* [Note.java](https://github.com/Me-Abhishek-patel/Notes-App/blob/master/app/src/main/java/com/ciberciti/notes/data/entities/Note.java)
* [User.java](https://github.com/Me-Abhishek-patel/Notes-App/blob/master/app/src/main/java/com/ciberciti/notes/data/entities/User.java)


**DAO**
* [NoteDAO.java](https://github.com/Me-Abhishek-patel/Notes-App/blob/master/app/src/main/java/com/ciberciti/notes/data/dao/NoteDAO.java)
* [UserDAO.java](https://github.com/Me-Abhishek-patel/Notes-App/blob/master/app/src/main/java/com/ciberciti/notes/data/dao/UserDAO.java)


**Database**
* [AppDatabase.java](https://github.com/Me-Abhishek-patel/Notes-App/blob/master/app/src/main/java/com/ciberciti/notes/data/db/AppDatabase.java)

