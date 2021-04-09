package com.eroaum.entities;

import java.awt.image.BufferedImage;

import com.eroaum.main.Game;

public class Collision extends Player {
	public Collision(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}

	public static void checkCollisionLifePack() {
		for (int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if (atual instanceof Life) {
				if (Entity.isColiding(Game.player, atual)) {
					Game.player.life = Game.player.maxlife;
					Game.entities.remove(atual);
				}
			}
		}

	}

	public static void checkCollisionMud() {
		for (int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if (atual instanceof Mud) {
				if (Entity.isColiding(Game.player, atual)) {
					Game.player.playerMud = true;
					Game.player.playerSlime=false;
					for (int u = 0; u < 3; u++) {
						Game.player.rightPlayer[u] = Game.spritesheet.getSprite(16 * 3 + (u * 16), 32, 16, 16);
						Game.player.leftPlayer[u] = Game.spritesheet.getSprite(0 + (u * 16), 32, 16, 16);
						Game.player.upPlayer[u] = Game.spritesheet.getSprite(0 + (u * 16), 16 * 4, 16, 16);
						Game.player.downPlayer[u] = Game.spritesheet.getSprite(0 + (u * 16), 16 * 3, 16, 16);

					}
					Game.entities.remove(atual);
				}
			}
		}
	}
}
