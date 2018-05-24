package testPrograms;

import java.io.IOException;

import org.junit.Test;

import program.Manifest;

public class ManifestClassTests {

	Manifest manifest;

	@Test
	public void exportManifest() throws IOException{
		manifest = new Manifest();
		manifest.generateManifest();
		
	}
}
