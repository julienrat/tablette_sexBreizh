/*
Application borne de lecture vidéo 
sous licence publique générale GNU, (GNU General Public License v3)
Julien Rat pour l'Association Française des Petits Débrouillards 

Copyright (C) 2014	Julien Rat

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 3 of the License, or
any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*/


//Bibliothèque pour la lecture des vidéos
import apwidgets.*; 
APVideoView videoView; 
APWidgetContainer container; 
//Bibliothèque de gestion de la fenetre
import android.view.WindowManager;
import android.os.Bundle;
import android.os.Environment;

int duree_video; // variable durée vidéo
void setup()
{
  size(displayWidth, displayHeight); // on définit la taille de l'ecran maximale

  orientation(LANDSCAPE); // orientation en mode paysage
  container = new APWidgetContainer(this); //Création d'un conteneur pour la vidéo
  videoView = new APVideoView(0, 0, displayWidth, displayHeight, true); //création d'un widget vidéo
  container.addWidget(videoView); //on place le widget video dans le conteneur
  
  videoView.setVideoPath("/storage/extSdCard/sexbreizh.avi"); // chemin du fichier vidéo (a modifier selon les tablettes...
  videoView.start(); //on demarre la vidéo
  videoView.seekTo(0); //on rembobine au début
  delay(1000); //delais necessaire au chargement de la vidéo
  duree_video=videoView.getDuration(); //on récupere la durée de la vidéo
  delay(300); //un delais de 300ms avant la pause
  videoView.pause(); // mise en pause de la vidéo
}

void draw()
{ 
  if (videoView.getCurrentPosition() > duree_video - 50) { // lorsque l'on arrive a la fin de la vidéo
    videoView.seekTo(0); // on rembobine
    delay(300); // un petit delais
    videoView.pause(); // mise en pause
  }
}



void mousePressed() { // si on appuie sur l'ecran
  videoView.seekTo(0); // on rembobine
  videoView.start(); // on lance la vidéo
  
}


//////////////////////////////*fonctions demarrage / arret de l'application *//////////////////////////
@Override
public void onCreate(Bundle savedInstanceState) { //fonction lancée au démarrage de l'application
  super.onCreate(savedInstanceState);
  getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // empeche la mise en veille
}

public void onDestroy(Bundle savedInstanceState) {  //fonction lancée à la fermeture de l'application
  super.onDestroy(); 
  getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // on remet la mise en veille active
}

