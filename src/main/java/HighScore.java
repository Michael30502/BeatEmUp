import processing.core.PApplet;
import processing.data.Table;

import java.util.ArrayList;
import java.util.Collections;

public class HighScore extends Menu{
    BeatEmUp beatEmUp;
    boolean calBest = false;
    String[] score = {"...","...","...","...","...", "..."};
    HighScore(PApplet p, BeatEmUp beatEmUp) {
        super(p);
        visible = false;
        this.beatEmUp = beatEmUp;
        Button btnBack = new Button(300,400,200,50,"Back",p);
        btnBack.addAction(new Action() {
            @Override
            public void execute() {
                visible = false;
                Main.mainMenu.visible = true;
             //   p.println(Main.mainMenu.visible);
              //  p.println(visible);
            }
        });
        btnList.add(btnBack);
    }


    @Override
    void draw() {
        if(calBest){
            calBestScore();
            calBest = false;
        }
        p.textSize(16*size);
        Table s = beatEmUp.scores;
        String header = s.getString(0,0)+ " | " + s.getString(0,1);
        p.text(header,400,180);
        for(int i = 0; i<score.length;++i){
            String info = score[i];
            p.text(info,400 *size,(200+16*i+10)*size);
        }


    }
    void calBestScore(){

        ArrayList<Score> bestScores = new ArrayList<>();
        Table s = beatEmUp.scores;
        for(int i = 2; i<s.getRowCount(); ++i){

            Score score = new Score(s.getString(i,0),s.getInt(i,1));
            bestScores.add(score);
        }

        bestScores = scoreBubbleSort(bestScores);
        Collections.reverse(bestScores);

        for(int i = 0; i<score.length;++i){

            if (i < bestScores.size()){
                String info = bestScores.get(i).name+ " | " + bestScores.get(i).score;
                score[i] = info;
            }
        }



    }

    ArrayList<Score> scoreBubbleSort(ArrayList<Score> bestScores) {
        boolean sorted = false;
        int temp;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < bestScores.size() - 1; i++) {
                if ( bestScores.get(i).score> bestScores.get(i+1).score) {
                    temp = bestScores.get(i).score;
                    bestScores.get(i).score = bestScores.get(i+1).score;
                    bestScores.get(i+1).score = temp;
                    sorted = false;
                }
            }
        }

        return bestScores;
    }

    @Override
    void mousePushed() {

    }
}