package com.kraqqen.core.renderer;

import com.kraqqen.util.math.Matrix4f;

public class RenderInfo {
	public Matrix4f projection, view, world;

	public RenderInfo(Matrix4f projection, Matrix4f view, Matrix4f world) {
		this.projection = projection;
		this.view = view;
		this.world = world;
	}
}
