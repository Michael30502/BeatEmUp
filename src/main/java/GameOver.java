import processing.core.PApplet;

public class GameOver extends Menu{


    GameOver(PApplet p) {
        super(p);

        visible = false;

        Button btnBack = new Button(400,300,200,50,"Back to menu",p);
        btnBack.addAction(new Action() {
            @Override
            public void execute() {
                visible = false;
                Main.gameEndScreen.visible = true;
                Main.beatEmUp.getScores();
                Main.beatEmUp.startUp();
            }
        });

        btnList.add(btnBack);

        Button btnContinue= new Button(400,200,200,50,"Continue",p);
        btnContinue.addAction(new Action() {
            @Override
            public void execute() {
                visible = false;
                Main.beatEmUp.getScores();
                Main.beatEmUp.startUp();
              Main.beatEmUp.visible = true;



            }
        });
        btnList.add(btnContinue);
    }

    @Override
    void draw() {
        p.textSize(20);
        p.text("DIN SCORE"+"  "+Main.beatEmUp.gameScore,500,500);
        p.textSize(12);
    }

    @Override
    void mousePushed() {

    }
}
