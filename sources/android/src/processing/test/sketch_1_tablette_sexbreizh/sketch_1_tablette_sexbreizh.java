package processing.test.sketch_1_tablette_sexbreizh;

import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import apwidgets.*; 
import android.view.WindowManager; 
import android.os.Bundle; 
import android.os.Environment; 

import apwidgets.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class sketch_1_tablette_sexbreizh extends PApplet {

//Biblioth\u00e8que pour la lecture des vid\u00e9os
 
APVideoView videoView; 
APWidgetContainer container; 
//Biblioth\u00e8que de gestion de la fenetre




int duree_video; // variable dur\u00e9e vid\u00e9o
public void setup()
{
  // on d\u00e9finit la taille de l'ecran maximale

  orientation(LANDSCAPE); // orientation en mode paysage
  container = new APWidgetContainer(this); //Cr\u00e9ation d'un conteneur pour la vid\u00e9o
  videoView = new APVideoView(0, 0, displayWidth, displayHeight, true); //cr\u00e9ation d'un widget vid\u00e9o
  container.addWidget(videoView); //on place le widget video dans le conteneur
  
  videoView.setVideoPath("/storage/extSdCard/sexbreizh.avi"); // chemin du fichier vid\u00e9o (a modifier selon les tablettes...
  videoView.start(); //on demarre la vid\u00e9o
  videoView.seekTo(0); //on rembobie au d\u00e9but
  delay(1000); //delais necessaire au chargement de la vid\u00e9o
  duree_video=videoView.getDuration(); //on r\u00e9cupere la dur\u00e9e de la vid\u00e9o
  delay(300); //un delais de 300ms avant la pause
  videoView.pause(); // mise en pause de la vid\u00e9o
}

public void draw()
{ 
  if (videoView.getCurrentPosition() > duree_video - 50) { // lorsque l'on arrive a la fin de la vid\u00e9o
    videoView.seekTo(0); // on rembobine
    delay(300); // un petit delais
    videoView.pause(); // mise en pause
  }
}



public void mousePressed() { // si on appuie sur l'ecran
  videoView.seekTo(0); // on rembobine
  videoView.start(); // on lance la vid\u00e9o
  
}


//////////////////////////////*fonctions demarrage / arret de l'application *//////////////////////////
@Override
public void onCreate(Bundle savedInstanceState) { //fonction lanc\u00e9e au d\u00e9marrage de l'application
  super.onCreate(savedInstanceState);
  getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // empeche la mise en veille
}

public void onDestroy(Bundle savedInstanceState) {  //fonction lanc\u00e9e \u00e0 la fermeture de l'application
  super.onDestroy(); 
  getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // on remet la mise en veille active
}


  public int sketchWidth() { return displayWidth; }
  public int sketchHeight() { return displayHeight; }
}
