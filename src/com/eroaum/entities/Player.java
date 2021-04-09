package com.eroaum.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.eroaum.graficos.Spritesheet;
import com.eroaum.main.Game;
import com.eroaum.world.Camera;
import com.eroaum.world.World;

public class Player extends Entity {
	public boolean right, up, left, down;
	public int right_dir = 0, left_dir = 1, up_dir = 2, down_dir = 3;
	public int dir = right_dir;
	public double speed = 1.4;
	private int frames = 0, maxFrames = 5, index = 0, maxindex = 2;
	private boolean moved = false;
	public int maskx = 8, masky = 8, maskh = 10, maskw = 10;
	public BufferedImage[] rightPlayer;
	public BufferedImage[] leftPlayer;
	public BufferedImage[] upPlayer;
	public BufferedImage[] downPlayer;
	private BufferedImage playerDamage;
	private static BufferedImage Mudshockwave_L, Mudshockwave_D, Mudshockwave_U, Mudshockwave_R, Mudshockwave_LD,
			Mudshockwave_LU, Mudshockwave_RU, Mudshockwave_RD;
	private static BufferedImage mudShield;
	public boolean isDamaged = false,mudAtack = false;
	public int damageFrames = 0, DefenseFrames = 0;
	public double life = 100, maxlife = 100;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		rightPlayer = new BufferedImage[3];
		leftPlayer = new BufferedImage[3];
		upPlayer = new BufferedImage[3];
		downPlayer = new BufferedImage[3];
		playerDamage = Game.spritesheet.getSprite(0, 144, 16, 16);
		mudShield = Game.spritesheet.getSprite(0, 128, 16, 16);
		for (int i = 0; i < 3; i++) {
			rightPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16), 0, 16, 16);
			leftPlayer[i] = Game.spritesheet.getSprite(80 + (i * 16), 0, 16, 16);
			upPlayer[i] = Game.spritesheet.getSprite(48 + (i * 16), 16, 16, 16);
			downPlayer[i] = Game.spritesheet.getSprite(0 + (i * 16), 16, 16, 16);
		}
	}

	public void Mudattack() {

		Mudshockwave_L = Game.spritesheet.getSprite(3 * 16, 128, 16, 16);
		Mudshockwave_R = Game.spritesheet.getSprite(3 * 16, 128, 16, 16);
		Mudshockwave_U = Game.spritesheet.getSprite(2 * 16, 128, 16, 16);
		Mudshockwave_D = Game.spritesheet.getSprite(2 * 16, 128, 16, 16);
		Mudshockwave_LD = Game.spritesheet.getSprite(5 * 16, 128, 16, 16);
		Mudshockwave_LU = Game.spritesheet.getSprite(4 * 16, 128, 16, 16);
		Mudshockwave_RU = Game.spritesheet.getSprite(4 * 16, 128, 16, 16);
		Mudshockwave_RD = Game.spritesheet.getSprite(5 * 16, 128, 16, 16);

	}

	public void tick() {
		moved = false;
		if (right && World.isFree((int) (x + speed), this.gety())) {
			moved = true;
			dir = right_dir;
			x += speed;
		} else if (left && World.isFree((int) (x - speed), this.gety())) {
			moved = true;
			dir = left_dir;
			x -= speed;
		}
		if (up && World.isFree(this.getx(), (int) (y - speed))) {
			moved = true;
			dir = up_dir;
			y -= speed;
		} else if (down && World.isFree(this.getx(), (int) (y + speed))) {
			moved = true;
			dir = down_dir;
			y += speed;
		}
		if (moved) {
			frames++;
			if (frames == maxFrames) {
				frames = 0;
				index++;
				if (index > maxindex)
					index = 0;
			}
		}
		Collision.checkCollisionMud();
		Collision.checkCollisionLifePack();
		if (isDamaged) {
			this.damageFrames++;
			if (this.damageFrames == 3) {
				this.damageFrames = 0;
				isDamaged = false;
			}

		}
		if(mudAtack == true) {
			mudAtack = false;
			System.out.println("atacando");
		Mudshockwave_L  = new BufferedImage(16,16,16);
			
		}

		if (life <= 0) {
			Game.entities.clear();
			Game.enemies.clear();
			Game.entities = new ArrayList<Entity>();
			Game.enemies = new ArrayList<Enemy>();
			Game.spritesheet = new Spritesheet("/spritesheet.png");
			Game.player = new Player(0, 0, 16, 16, Game.spritesheet.getSprite(32, 0, 16, 16));
			Game.entities.add(Game.player);
			Game.world = new World("/map.png");

		}
		Camera.x = Camera.clamp(this.getx() - (Game.WIDTH / 2), 0, World.WIDTH * 16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.gety() - (Game.HEIGHT / 2), 0, World.HEIGHT * 16 - Game.HEIGHT);

	}

	public void render(Graphics g) {
		if (dir == right_dir) {
			g.drawImage(rightPlayer[index], this.getx() - Camera.x, this.gety() - Camera.y, null);
		} else if (dir == left_dir) {
			g.drawImage(leftPlayer[index], this.getx() - Camera.x, this.gety() - Camera.y, null);
		} else if (dir == up_dir) {
			g.drawImage(upPlayer[index], this.getx() - Camera.x, this.gety() - Camera.y, null);
		} else if (dir == down_dir) {
			g.drawImage(downPlayer[index], this.getx() - Camera.x, this.gety() - Camera.y, null);
		}
		if (isDamaged) {
			g.drawImage(playerDamage, this.getx() - Camera.x, this.gety() - Camera.y, null);
		}
		if (this.Defense == true) {
			if (Game.player.playerMud == true) {
				g.drawImage(mudShield, this.getx() - Camera.x, this.gety() - Camera.y, null);
			}
		}
		if (mudAtack == true) {
			
			//g.drawImage(Mudshockwave_L, (this.getx() - 16)- Camera.x, this.gety() - Camera.y, null);
			System.out.println("klkkk");
			
		}

	}

}
