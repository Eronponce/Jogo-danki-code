package com.eroaum.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.eroaum.main.Game;
import com.eroaum.world.Camera;
import com.eroaum.world.World;

public class Enemy extends Entity {
	private double speed = 0.5;
	private int maskx = 8, masky = 8, maskh = 10, maskw = 10;

	private int frames = 0, maxFrames = 10, index = 0, maxindex = 2;

	private BufferedImage[] sprites;

	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, null);
		sprites = new BufferedImage[3];
		sprites[0] = Game.spritesheet.getSprite(112 - 16, 16, 16, 16);
		sprites[1] = Game.spritesheet.getSprite(112, 16, 16, 16);
		sprites[2] = Game.spritesheet.getSprite(112 + 16, 16, 16, 16);

	}

	public void tick() {
		if (this.isColidingWithPlayer() == false) {
			if (Game.rand.nextInt(100) < 90) {
				if ((int) x < Game.player.getx() && World.isFree((int) (x + speed), this.gety())
						&& !isColiding((int) (x + speed), this.gety())) {
					x += speed;
				} else if ((int) x > Game.player.getx() && World.isFree((int) (x - speed), this.gety())
						&& !isColiding((int) (x - speed), this.gety())) {
					x -= speed;
				}
				if ((int) y < Game.player.gety() && World.isFree(this.getx(), (int) (y + speed))
						&& !isColiding(this.getx(), (int) (y + speed))) {
					y += speed;
				} else if ((int) y > Game.player.gety() && World.isFree(this.getx(), (int) (y - speed))
						&& !isColiding(this.getx(), (int) (y - speed))) {
					y -= speed;
				}
			}
			frames++;
			if (frames == maxFrames) {
				frames = 0;
				index++;
				if (index > maxindex)
					index = 0;
			}
		} else {
			frames++;
			if (frames == maxFrames) {
				frames = 0;
				index++;
				if (index > maxindex)
					index = 0;
			}
			
			
			if (Game.player.playerSlime == true) {
				if (Game.rand.nextInt(100) < 10) {
					Game.player.life -= 10;
					Game.player.isDamaged = true;
				}
			}else if (Game.player.playerMud == true&& Game.player.Defense == false) {
				if (Game.rand.nextInt(100) < 10) {
					Game.player.life -= 5;
					Game.player.isDamaged = true;
				}
			}else if (Game.player.playerMud == true && Game.player.Defense == true) {
				if (Game.rand.nextInt(100) < 30) {
					Game.player.life --;					
				}
			}
			
			
			
		}
	}

	public boolean isColidingWithPlayer() {
		Rectangle enemyCurrent = new Rectangle(this.getx() + maskx, this.gety() + masky, maskh, maskw);
		Rectangle player = new Rectangle(Game.player.getx() + Game.player.maskx, Game.player.gety() + Game.player.masky,
				Game.player.maskh, Game.player.maskw);

		return enemyCurrent.intersects(player);
	}

	public boolean isColiding(int xnext, int ynext) {
		Rectangle enemyCurrent = new Rectangle(xnext + maskx, ynext + masky, maskw, maskh);

		for (int i = 0; i < Game.enemies.size(); i++) {
			Enemy e = Game.enemies.get(i);
			if (e == this)
				continue;
			Rectangle targetEnemy = new Rectangle(e.getx() + maskx, e.gety() + masky, maskw, maskh);
			if (enemyCurrent.intersects(targetEnemy)) {
				return true;
			}
		}
		return false;
	}

	public void render(Graphics g) {
		g.drawImage(sprites[index], this.getx() - Camera.x, this.gety() - Camera.y, null);

	}
}
