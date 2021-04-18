//laver main menuen
public class MainMenu extends Menu {

  BeatEmUp beatEmUp;
  Infoscreen infoscreen;
  Credits credits;
  HighScore highscore;


         MainMenu(Main p, BeatEmUp beatEmUp, Infoscreen infoscreen, Credits credits, HighScore highscore) {
             super(p);
             this.beatEmUp = beatEmUp;
             this.infoscreen = infoscreen;
             this.credits = credits;
             this.highscore = highscore;


                 Button btnPlay = new Button(200, 200, 200, 50, "Play", p);
                 btnPlay.addAction(new Action() {
                     @Override
                     public void execute() {
                         visible = false;
                         beatEmUp.visible = true;
                        // p.println(visible);
                        // p.println(beatEmUp.visible);


                     }
                 });

                 btnList.add(btnPlay);


                 Button btnInfo = new Button(200, 300, 200, 50, "Info", p);
                 btnInfo.addAction(new Action() {
                     @Override
                     public void execute() {
                         visible = false;
                         infoscreen.visible = true;
                       //  p.println(visible);
                       //  p.println(infoscreen.visible);

                     }
                 });

                 btnList.add(btnInfo);

                 Button btnCredits = new Button(200,400,200,50,"Credits",p);
                 btnCredits.addAction(new Action() {
                     @Override
                     public void execute() {
                         visible = false;
                         credits.visible = true;
                     }
                 });
                 btnList.add(btnCredits);

                 Button btnHighscore = new Button(200,500,200,50,"Highscores",p);
                 btnHighscore.addAction(new Action() {
                     @Override
                     public void execute() {
                         visible = false;
                         highscore.visible = true;
                     }
                 });
                 btnList.add(btnHighscore);
                 visible = true;

             }




        @Override
        void draw() {

        }

        @Override
        void mousePushed() {

        }


    }


