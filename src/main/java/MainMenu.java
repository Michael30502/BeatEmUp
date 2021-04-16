import processing.core.PApplet;
public class MainMenu extends Menu {

  BeatEmUp beatEmUp;




         MainMenu(Main p, BeatEmUp beatEmUp) {
             super(p);
             this.beatEmUp = beatEmUp;


             Button btnPlay = new Button(200, 200, 200, 50, "Play", p);
             btnPlay.addAction(new Action() {
                 @Override
                 public void execute() {
                     visible = false;
                     beatEmUp.visible = true;

                 }
             });

             btnList.add(btnPlay);


         }

        @Override
        void draw() {

        }

        @Override
        void mousePushed() {

        }


    }


