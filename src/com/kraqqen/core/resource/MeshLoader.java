package com.kraqqen.core.resource;

import java.util.ArrayList;
import java.util.List;

import com.kraqqen.core.arch.Mesh;
import com.kraqqen.util.file_system.FileUtil;
import com.kraqqen.util.math.Vector2f;
import com.kraqqen.util.math.Vector3f;

public class MeshLoader {
	public static Mesh Load(String path)
	{
		Mesh mesh = new Mesh();
		
		List<Vector3f> vertices = new ArrayList<Vector3f>();
		List<Integer> indices = new ArrayList<Integer>();
		List<Vector3f> normals = new ArrayList<Vector3f>();
		List<Vector2f> texcoords = new ArrayList<Vector2f>();
		
		String[] lines = FileUtil.readFromFile(path).split("\n");
		for(int i = 0; i < lines.length; i++)
		{
			String line = lines[i];
			if(line.startsWith("#")) //a comment
			{
				continue;
			} else if(line.startsWith("v"))
			{
				vertices.add(parseVec3(line.split("-")[1].trim()));
			}
		}
		
		//NOT FINISHED YET
		
		return mesh;
	}
	
	private static Vector3f parseVec3(String str)
	{
		String[] t = str.split(" ");
		return new Vector3f(Float.parseFloat(t[0]), Float.parseFloat(t[1]), Float.parseFloat(t[2]));
	}
}
