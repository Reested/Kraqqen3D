package com.kraqqen.editor;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import org.lwjgl.opengl.GL;

import com.kraqqen.core.arch.Mesh;
import com.kraqqen.core.renderer.RenderInfo;
import com.kraqqen.core.renderer.ShaderProgram;
import com.kraqqen.core.renderer.Window;
import com.kraqqen.util.math.Matrix4f;

public class MainTestLoop {

	boolean running = true;

	private ShaderProgram shaderProgram;

	public void run() {
		Window window = new Window();
		window.show();
		
		shaderProgram = new ShaderProgram("res/engine/default.vs", "res/engine/default.fs");
		
		Mesh mesh = Mesh.QuadMesh();
		
		Matrix4f projection = new Matrix4f().InitPerspective(45, window.getAspectRatio(), 0.1f, 1000f);
		Matrix4f view = new Matrix4f().InitTranslation(0, 0, 5);
		Matrix4f world = new Matrix4f().InitIdentity();
		
		window.setOnClose(() -> {
			running = false;
		});
		float rot = 0;
		while (running) {
			GL.createCapabilities();
	        glClear(GL_COLOR_BUFFER_BIT);
	        view = new Matrix4f().InitTranslation(0, 0, 5).Mul(new Matrix4f().InitRotation(0, rot += 1f, 0));
	        shaderProgram.bind();
	        shaderProgram.setAttribute(ShaderProgram.MATRIX_ATTRIB, "projection", projection);
	        shaderProgram.setAttribute(ShaderProgram.MATRIX_ATTRIB, "view", view);
	        shaderProgram.setAttribute(ShaderProgram.MATRIX_ATTRIB, "world", world);
	        mesh.Render(new RenderInfo(projection, view, world));
	        ShaderProgram.unbind();
			window.Frame();
		}
		window.close();
	}

	public static void main(String[] args) {
		new MainTestLoop().run();
	}

}