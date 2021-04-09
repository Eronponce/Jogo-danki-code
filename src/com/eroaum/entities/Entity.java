package com.eroaum.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.eroaum.main.Game;
import com.eroaum.world.Camera;

public class Entity {
	public static BufferedImage MUD_EN = Game.spritesheet.getSprite(9 * 16, 0, 16, 16);
	public static BufferedImage LIFE_EN = Game.spritesheet.getSprite(8 * 16, 0, 16, 16);
	public static BufferedImage YELLOWENEMY_EN = Game.spritesheet.getSprite(6 * 16, 16, 16, 16);
	public boolean playerMud = false, playerSlime = true, Defense = false;
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	private BufferedImage sprite;
	private int maskx, masky, maskw, maskh;

	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.sprite = sprite;

		this.maskx = 0;
		this.masky = 0;
		this.maskw = width;
		this.maskh = height;
	}

	public void setMask(int maskx, int masky, int maskh, int maskw) {
		this.maskx = maskx;
		this.masky = masky;
		this.maskw = maskw;
		this.maskh = maskh;
	}

	public void setX(int newX) {
		this.x = newX;
	}

	public void setY(int newY) {
		this.y = newY;
	}

	public int getx() {
		return (int) this.x;
	}

	public int gety() {
		return (int) this.y;
	}

	public int getheight() {
		return this.height;
	}

	public int getwidth() {
		return this.width;
	}

	public void tick() {

	}

	public static boolean isColiding(Entity e1, Entity e2) {
		Rectangle e1Mask = new Rectangle(Game.player.getx() + Game.player.maskx, Game.player.gety() + Game.player.masky,
				Game.player.maskh, Game.player.maskw);
		Rectangle e2Mask = new Rectangle(e2.getx() + e2.maskx, e2.gety() + e2.masky, e2.maskw, e2.maskh);
		return e1Mask.intersects(e2Mask);
	}

	public void render(Graphics g) {
		g.drawImage(sprite, this.getx() - Camera.x, this.gety() - Camera.y, null);
		// g.setColor(Color.red);
		// g.fillRect(this.getx()+this.maskx-Camera.x,this.gety()+this.masky-Camera.y,
		// this.maskw,this.maskh);
	}
}
