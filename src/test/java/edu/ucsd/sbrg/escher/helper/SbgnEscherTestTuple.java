package edu.ucsd.sbrg.escher.helper;

import edu.ucsd.sbrg.escher.converters.SBGN2Escher;
import edu.ucsd.sbrg.escher.model.EscherMap;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.sbgn.SbgnUtil;
import org.sbgn.bindings.Sbgn;

import javax.xml.bind.JAXBException;
import java.io.File;

import static org.powermock.api.mockito.PowerMockito.spy;

/**
 * Created by deveshkhandelwal on 01/07/16.
 */
public class SbgnEscherTestTuple {

  public File      file;
  public Sbgn      sbgn;
  public EscherMap map;
  public SBGN2Escher converter = spy(new SBGN2Escher());
  public int       numberOfMetabolites;
  public int       numberOfReactions;


  public SbgnEscherTestTuple(File file) throws JAXBException {
    this.file = file;
    this.sbgn = SbgnUtil.readFromFile(file);
    this.map = converter.convert(this.sbgn);
    this.numberOfMetabolites = (int) sbgn.getMap()
                                         .getGlyph()
                                         .stream()
                                         .filter(n -> n.getClazz()
                                                 .matches("simple "
                                                     + "chemical|macromolecule|perturbing "
                                                     + "agent|process|omitted process|uncertain "
                                                     + "process|association|dissociation"))
                                         .count();
    this.numberOfReactions = (int) sbgn.getMap().getGlyph().stream().filter(n -> n.getClazz().contains
        ("process")).count();
  }
}
