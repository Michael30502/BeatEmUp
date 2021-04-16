import processing.core.PApplet;
public class MainMenu extends Menu {

  BeatEmUp beatEmUp;
  Infoscreen infoscreen;



         MainMenu(Main p, BeatEmUp beatEmUp,Infoscreen infoscreen) {
             super(p);
             this.beatEmUp = beatEmUp;
             this.infoscreen = infoscreen;


             Button btnPlay = new Button(200, 200, 200, 50, "Play", p);
             btnPlay.addAction(new Action() {
                 @Override
                 public void execute() {
                     visible = false;
                     beatEmUp.visible = true;

                 }
             });

             btnList.add(btnPlay);


            Button btnInfo = new Button(200, 300, 200, 50, "Info", p);
             btnInfo.addAction(new Action() {
                 @Override
                 public void execute() {
                     visible = false;
                    infoscreen.visible = true;

                 }
             });

             btnList.add(btnInfo);

            visible = true;
         }

        @Override
        void draw() {

        }

        @Override
        void mousePushed() {

        }


    }


