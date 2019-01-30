package com.cmscproject.collegerun.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.cmscproject.collegerun.Sprites.DistanceManager;
import com.cmscproject.collegerun.Sprites.HealthManager;
import com.cmscproject.collegerun.Sprites.MusicManager;
import com.cmscproject.collegerun.Sprites.ObstacleManager;
import com.cmscproject.collegerun.Sprites.Person;
import com.cmscproject.collegerun.Sprites.PlatformManager;
import com.cmscproject.collegerun.Sprites.PowerupManager;

/**
 * 	GameState
 * Class gamestate handles the main game class
 * @author Harrison Cook
 */

public class GameState extends States {
    private final Texture ground;
    private final Texture backGround;
    private final Person person;
    private final Sprite backGroundSprite;
    private final Sprite groundSprite;
    private final ObstacleManager obstacleManager;
    private final PowerupManager powerupManager;
    private final Texture pauseButton;
    private final float objectScroll;
    private final Texture healthBarOutline;
    private final Texture healthBar;
    private final Texture finishedFlag;
    private final HealthManager healthManager;
    private final PlatformManager platformManager;
    private final int groundHeight;
    private final float widthHeightOfBuffer;
    private int maxDIST;
    private final Vector2 flag;
    private int difficulty;
    private final int levelNum;
    private final DistanceManager distanceManager;
    private MusicManager musicManager;

    /**
     * Constructor initializes the game
     * @param gameStateManager gamestatemanager handles all the states
     */
    public GameState(GameStateManager gameStateManager, int levelNum, MusicManager musicManager){
        super(gameStateManager);
        healthManager = new HealthManager();
        groundHeight = Gdx.graphics.getHeight()/10;
        int yOfObstacle = groundHeight/2+groundHeight/20;
        int yOfPerson = groundHeight/2+groundHeight/5;
        float personScroll = 0.08f;
        this.musicManager = musicManager;
        musicManager.changeSong();
        this.levelNum = levelNum;
        setMaxDIST();
        difficultyPicker();
        objectScroll = Gdx.graphics.getWidth()/111.6f;
        Gdx.input.setCatchBackKey(true);
        distanceManager = new DistanceManager(maxDIST);
        person = new Person(Gdx.graphics.getWidth()/6, yOfPerson,Gdx.graphics.getWidth()/7,Gdx.graphics.getHeight()/4, personScroll);
        obstacleManager = new ObstacleManager(difficulty, objectScroll,yOfObstacle,person.getWidth()/1.65f,person.getHeight()/1.65f);
        powerupManager = new PowerupManager(difficulty, objectScroll,yOfObstacle);
        platformManager = new PlatformManager(Gdx.graphics.getHeight()/2.75f,objectScroll);
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        backGround = new Texture("background.png");
        ground  =new Texture("groundT.png");
        pauseButton = new Texture("pause.png");
        finishedFlag = new Texture("finish.png");
        flag  = new Vector2(Gdx.graphics.getWidth()*2, yOfObstacle);
        backGround.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        ground.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        backGroundSprite = new Sprite(backGround,0,0,backGround.getWidth(),backGround.getHeight());
        groundSprite = new Sprite(ground,0,0,ground.getWidth(),ground.getHeight());
        healthBarOutline = new Texture("coffeeBarEmpty.png");
        healthBar = new Texture("coffeeBarAnimations.png");
        widthHeightOfBuffer = (Gdx.graphics.getHeight()/15)*0.2213f;
    }


    /**
     * Method to handle input
     */
    @Override
    protected void handleInput() {

        if (Gdx.input.justTouched()){
            if(Gdx.input.getX()<=Gdx.graphics.getWidth()&&Gdx.input.getX()>=Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/15&&Gdx.input.getY()>=0&&Gdx.input.getY()<=Gdx.graphics.getWidth()/15){
                gameStateManager.add(new PauseScreen(gameStateManager, person,musicManager));
            }
            else{
                person.jump();

            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            musicManager.changeSong();
            gameStateManager.setState(new GamePickScreenState(gameStateManager,musicManager));
        }
    }

    private void difficultyPicker(){
        if(levelNum == 0){
            difficulty = 1;
        }
        else if(levelNum == 1||levelNum==2){
            difficulty = 1;
        }
        else if(levelNum==3||levelNum == 4){
            difficulty = 2;
        }
        else if(levelNum==5|| levelNum == 6){
            difficulty = 3;
        }
        else{
            difficulty = 4;
        }
    }

    private void setMaxDIST(){
        if(levelNum == 0){
            maxDIST = Integer.MAX_VALUE;
        }
        else if(levelNum == 1 || levelNum == 3 || levelNum == 5 || levelNum == 7){
            maxDIST = 2000;
        }
        else if(levelNum == 2 || levelNum == 4 || levelNum == 6 || levelNum == 8){
            maxDIST = 3000;
        }
    }



    /**
     * Method to update game
     */
    @Override
    public void update(float dt) {
        handleInput();

        person.update(dt);
        distanceManager.update();
        if(distanceManager.getDist()%2000==0&&maxDIST == Integer.MAX_VALUE && difficulty!=4) {
            difficulty = difficulty+1;
            powerupManager.changeDifficulty(difficulty);
            obstacleManager.changeDifficulty(difficulty);
        }
        if(distanceManager.maxDistNotReached()) {
            obstacleManager.update(true);
            powerupManager.update(true);
            platformManager.update(true,person,powerupManager);
            healthManager.lowerEnergy();
        }
        else{
            powerupManager.update(false);
            obstacleManager.update(false);
            platformManager.update(false,person,powerupManager);
            flag.add(-objectScroll,0);
        }
        if(obstacleManager.getObstacles().size>0&&person.getBounds()!=null&&obstacleManager.getObstacles().last().getBound()!=null) {
            for (int i = 0;i<obstacleManager.getObstacles().size;i++) {
                if (person.getBounds().overlaps(obstacleManager.getObstacles().get(i).getBound())) {
                    dispose();
                    musicManager.changeSong();
                    gameStateManager.setState(new GameOverScreen(gameStateManager, "GAME OVER",levelNum,distanceManager.getDist(),musicManager));
                }
            }
        }
        if(powerupManager.getPowerups().size>0&&person.getBounds()!=null&&powerupManager.getPowerups().last().getBounds()!=null) {
            for (int i =0; i<powerupManager.getPowerups().size;i++) {
                if (person.getBounds().overlaps(powerupManager.getPowerups().get(i).getBounds())) {
                    healthManager.addEnergy(powerupManager.getPowerups().get(i).getRestoreValue());
                    powerupManager.removePowerUp(i);
                }
            }
        }
        if(powerupManager.getPowerups().size>0&&obstacleManager.getObstacles().size>0&&powerupManager.getPowerups().last().getBounds()!=null&&obstacleManager.getObstacles().last().getBound()!=null){
            for (int i =0; i<powerupManager.getPowerups().size;i++) {
                for(int j = 0;j<obstacleManager.getObstacles().size;j++){
                    if (powerupManager.getPowerups().get(i).getBounds().overlaps(obstacleManager.getObstacles().get(j).getBound())){
                        powerupManager.removePowerUp(i);
                    }
                }
            }
        }
        if(healthManager.getEnergy()<=0){
            dispose();
            musicManager.changeSong();
            gameStateManager.setState(new GameOverScreen(gameStateManager, "GAME OVER",levelNum,distanceManager.getDist(),musicManager));
        }
        if(flag.x < person.getPosition().x){
            dispose();
            musicManager.changeSong();
            gameStateManager.setState(new GameOverScreen(gameStateManager,"YOU WIN!", levelNum, distanceManager.getDist(),musicManager));
        }
        camera.update();

    }




    /**
     * Method to render the images
     * @param spriteBatch images to handle in libgdx
     */
    @Override
    protected void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(backGroundSprite,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        spriteBatch.draw(groundSprite,0,0,Gdx.graphics.getWidth(),groundHeight);
        float scrollSpeed = 0.009f;
        spriteBatch.draw(person.getTexture(scrollSpeed),person.getPosition().x,person.getPosition().y, person.getWidth(),person.getHeight());
        spriteBatch.draw(healthBarOutline, 0,Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/15,Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/15);
        spriteBatch.draw(healthBar,widthHeightOfBuffer, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/15+widthHeightOfBuffer,healthManager.drawSize(widthHeightOfBuffer),Gdx.graphics.getHeight()/15-widthHeightOfBuffer*2);
        if(levelNum>0){
            spriteBatch.draw(distanceManager.getProgressBar(),Gdx.graphics.getWidth()/3+Gdx.graphics.getWidth()/15+widthHeightOfBuffer, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/15,distanceManager.drawSize(widthHeightOfBuffer),Gdx.graphics.getHeight()/15-widthHeightOfBuffer);
            spriteBatch.draw(distanceManager.getEmptyProgress(), Gdx.graphics.getWidth()/3+Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/15,Gdx.graphics.getWidth()/2-Gdx.graphics.getWidth()/15, Gdx.graphics.getHeight()/15);
        }
        for(int  i =0; i<obstacleManager.getObstacles().size;i++){
            spriteBatch.draw(obstacleManager.getObstacles().get(i).getOb(), obstacleManager.getObstacles().get(i).getPositionOb().x,obstacleManager.getObstacles().get(i).getPositionOb().y,obstacleManager.getObstacles().get(i).getWidth(),obstacleManager.getObstacles().get(i).getHeight());
        }
        for(int  i =0; i<platformManager.getPlatformQueue().size;i++){
            spriteBatch.draw(platformManager.getPlatformQueue().get(i).getPlatformT(),platformManager.getPlatformQueue().get(i).getPosition().x,platformManager.getPlatformQueue().get(i).getPosition().y,Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()/15);
        }
        spriteBatch.draw(pauseButton,Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/15,Gdx.graphics.getHeight()-Gdx.graphics.getWidth()/15,Gdx.graphics.getWidth()/15,Gdx.graphics.getWidth()/15);
        for(int  i =0; i<powerupManager.getPowerups().size;i++)
        {
            spriteBatch.draw(powerupManager.getPowerups().get(i).getTexture(), powerupManager.getPowerups().get(i).getPosition().x, powerupManager.getPowerups().get(i).getPosition().y+person.getHeight()/3,
                    person.getWidth()/2.5f, person.getHeight()/1.75f);
        }
        if (distanceManager.gameOver()){
            spriteBatch.draw(finishedFlag, flag.x,flag.y,person.getWidth(),person.getWidth());
        }
        spriteBatch.end();
        backGroundSprite.scroll(scrollSpeed, 0);
        groundSprite.scroll(0.00894f, 0);
    }

    /**
     * Method disposes images
     */
    @Override
    public void dispose(){
        healthBarOutline.dispose();
        finishedFlag.dispose();
        healthBar.dispose();
        pauseButton.dispose();
        person.dispose();
        backGround.dispose();
        ground.dispose();

    }
}
