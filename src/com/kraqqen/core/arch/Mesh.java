package com.kraqqen.core.arch;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

import com.kraqqen.core.renderer.RenderInfo;
import com.kraqqen.util.math.Vector2f;
import com.kraqqen.util.math.Vector3f;

public class Mesh extends Renderable {
	
	private int VBO, VAO, EBO;
	
	public Vector3f[] 	m_vertices;
	public int[] 		m_indices;
	public Vector3f[] 	m_normals;
	public Vector2f[]	m_texcoords;
	
	public Mesh() {}
	
	public Mesh(Vector3f[] vertices, int[] indices, Vector3f[] normals, Vector2f[] texcoords)
	{
		//TODO: Normals are not yet implemented
		//TODO: Texture coordinates are not yet implemented
		m_vertices = vertices;
		m_indices = indices;
		m_normals = normals;
		m_texcoords = texcoords;
		
		initRenderData();
	}
	
	public static Mesh QuadMesh()
	{
		Mesh mesh = new Mesh();
		
		mesh.m_vertices = new Vector3f[] {
			new Vector3f(-1, -1, 0),
			new Vector3f(-1,  1, 0),
			new Vector3f( 1,  1, 0),
			new Vector3f( 1, -1, 0),
		};
		
		mesh.m_indices = new int[] {
			0, 1, 3,
			1, 2, 3
		};
		
		mesh.initRenderData();
		
		return mesh;
	}
	
	public void initRenderData()
	{
		VAO = glGenVertexArrays();
		glBindVertexArray(VAO);

		VBO = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, VBO);
		glBufferData(GL_ARRAY_BUFFER, createVectorBuffer(m_vertices), GL_STATIC_DRAW);
		
		EBO = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, EBO);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, createIntBuffer(m_indices), GL_STATIC_DRAW);

		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		glBindVertexArray(0);
	}
	
	private static FloatBuffer createVectorBuffer(Vector3f[] vectors)
	{
		FloatBuffer b = BufferUtils.createFloatBuffer(vectors.length * 3);
		for(int i = 0; i < vectors.length; i++)
		{
			b.put(vectors[i].GetX());
			b.put(vectors[i].GetY());
			b.put(vectors[i].GetZ());
		}
		b.flip();
		return b;
	}
	
	private static IntBuffer createIntBuffer(int[] data)
	{
		IntBuffer b = BufferUtils.createIntBuffer(data.length);
		b.put(data);
		b.flip();
		return b;
	}

	@Override
	public void Render(RenderInfo info) {
        glBindVertexArray(VAO);
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, EBO);
        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
	}
}
