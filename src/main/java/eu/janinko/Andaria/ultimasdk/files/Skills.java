
package eu.janinko.Andaria.ultimasdk.files;

import eu.janinko.Andaria.ultimasdk.files.gumps.Gump;
import eu.janinko.Andaria.ultimasdk.files.skills.Skill;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Honza Brázdil <jbrazdil@redhat.com>
 */
public class Skills {
	private FileIndex fileIndex;
	private File skillmul;
	
	public Skills(InputStream skillidx, File skillmul) throws IOException{
		fileIndex = new FileIndex(skillidx, skillmul, 100);
		this.skillmul = skillmul;
	}

	public void save(OutputStream skillid, OutputStream skillmul) throws IOException{
		fileIndex.save(skillid, skillmul);
	}

	public Skill getSkill(int index) throws IOException{
		FileIndex.DataPack data =  fileIndex.getData(index);
		if(data == null) return null;

		Skill skill = new Skill(data.getNewStream(), data.getData().length);

		return skill;
	}

	public void setSkill(int i, Skill skill, OutputStream skillidx) throws IOException {
		File f = new File(skillmul.getAbsolutePath() + ".new");
		byte[] data = skill.write();
		int extra = 0;
		FileIndex.DataPack dp = new FileIndex.DataPack(data, extra);
		fileIndex.save(skillidx, new FileOutputStream(f) , i, dp);
		System.out.println("Renaming: " + f.renameTo(skillmul));
	}
}
