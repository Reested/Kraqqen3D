package com.kraqqen.core.renderer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;

import com.kraqqen.util.file_system.FileUtil;
import com.kraqqen.util.math.Matrix4f;

public class ShaderProgram
{
	public static final int MATRIX_ATTRIB = 0;
    private int programID;
    private int vertexShaderID;
    private int fragmentShaderID;

    public ShaderProgram(String vertexShaderPath, String fragmentShaderPath)
    {
		GL.createCapabilities();
        programID = glCreateProgram();
        attachVertexShader(FileUtil.readFromFile(vertexShaderPath));
        attachFragmentShader(FileUtil.readFromFile(fragmentShaderPath));
        link();
    }
    
    public void setAttribute(int attribType, String attribName, Object attrib)
    {
    	int location = glGetUniformLocation(programID, attribName);
    	if(attribType == MATRIX_ATTRIB)
    	{
    		glUniformMatrix4fv(location, true, createMatrixBuffer((Matrix4f)attrib));
    	}
    }
    
    private static FloatBuffer createMatrixBuffer(Matrix4f matrix)
    {
    	FloatBuffer buffer = BufferUtils.createFloatBuffer(16 /* 4 * 4 */);
    	float[][] data = matrix.GetM();
    	for(int i = 0; i < 4; i++)
    	{
    		buffer.put(data[i]);
    	}
    	buffer.flip();
    	return buffer;
    }

    private void attachVertexShader(String vertexShaderSource)
    {
        vertexShaderID = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShaderID, vertexShaderSource);
        glCompileShader(vertexShaderID);
        if (glGetShaderi(vertexShaderID, GL_COMPILE_STATUS) == GL_FALSE)
            throw new RuntimeException("Error creating vertex shader\n"
                                       + glGetShaderInfoLog(vertexShaderID, glGetShaderi(vertexShaderID, GL_INFO_LOG_LENGTH)));

        glAttachShader(programID, vertexShaderID);
    }
    
    private void attachFragmentShader(String fragmentShaderSource)
    {
        fragmentShaderID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShaderID, fragmentShaderSource);
        glCompileShader(fragmentShaderID);
        if (glGetShaderi(fragmentShaderID, GL_COMPILE_STATUS) == GL_FALSE)
            throw new RuntimeException("Error creating fragment shader\n"
                                       + glGetShaderInfoLog(fragmentShaderID, glGetShaderi(fragmentShaderID, GL_INFO_LOG_LENGTH)));

        glAttachShader(programID, fragmentShaderID);
    }

    private void link()
    {
        glLinkProgram(programID);
        if (glGetProgrami(programID, GL_LINK_STATUS) == GL_FALSE)
            throw new RuntimeException("Unable to link shader program:");
    }

    public void bind()
    {
        glUseProgram(programID);
    }
    
    public static void unbind()
    {
        glUseProgram(0);
    }

    public void dispose()
    {
        unbind();
        glDetachShader(programID, vertexShaderID);
        glDetachShader(programID, fragmentShaderID);
        glDeleteShader(vertexShaderID);
        glDeleteShader(fragmentShaderID);
        glDeleteProgram(programID);
    }
    
    public int getID()
    {
        return programID;
    }
}