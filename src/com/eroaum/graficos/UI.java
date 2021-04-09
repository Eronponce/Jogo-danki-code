package com.eroaum.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.eroaum.entities.Player;
import com.eroaum.main.Game;

public class UI {
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(4, 4, 50, 8);
		g.setColor(Color.green);
		g.fillRect(4, 4, (int) ((Game.player.life / Game.player.maxlife) * 50), 8);
		g.setColor(Color.white);
		g.setFont(new Font("comic Sans", Font.BOLD, 8));
		g.drawString((int) Game.player.life + "/" + (int) Game.player.maxlife, 15, 11);
	}

}
