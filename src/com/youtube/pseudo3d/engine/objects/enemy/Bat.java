package com.youtube.pseudo3d.engine.objects.enemy;

import com.youtube.pseudo3d.engine.GameLogic;
import com.youtube.pseudo3d.engine.objects.RandomMovingObject;
import com.youtube.pseudo3d.resource.Animator;
import com.youtube.pseudo3d.resource.TextureHolder;
import com.youtube.pseudo3d.resource.TextureHolder.ID;
import com.youtube.pseudo3d.util.Vector2d;

public class Bat extends RandomMovingObject implements Enemy{
	private Animator animator;
	private Animator deathAnimator;
	
	private boolean dying;
	
	public Bat(GameLogic raycaster, Vector2d position) {
		super(raycaster, position);
		animator = new Animator(TextureHolder.get(ID.ENEMY_BAT), 64, 64, 6);
		deathAnimator = new Animator(TextureHolder.get(ID.ENEMY_BAT_DYING), 64, 64, 6);
		
		health = 100;
	}
	
	@Override
	public void update(double elapsed) {
		super.update(elapsed);
				
		if(health <= 0)
			dying = true;
		
		int duration = 10;
		int deathDuration = 20;
		if(dying) {
			texture = deathAnimator.getCurrentFrame()[(raycaster.time / deathDuration) % deathAnimator.getCurrentFrame().length];
			if((raycaster.time / deathDuration) % (deathAnimator.getCurrentFrame().length) == 5) {
				dead = true;			
				raycaster.getGameObjects().add(new BatCorpse(raycaster, position));
			}
		}
		else
			texture = animator.getCurrentFrame()[(raycaster.time / duration) % animator.getCurrentFrame().length];
	
	}
}
