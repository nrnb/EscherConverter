/*
 * $Id$
 * $URL$
 * ---------------------------------------------------------------------
 * This file is part of the program BioNetView.
 *
 * Copyright (C) 2013-2016 by the University of California, San Diego.
 *
 * This library is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation. A copy of the license
 * agreement is provided in the file named "LICENSE.txt" included with
 * this software distribution and also available online as
 * <http://www.gnu.org/licenses/lgpl-3.0-standalone.html>.
 * ---------------------------------------------------------------------
 */
package edu.ucsd.sbrg.escher.utilities;

import de.zbit.io.filefilter.MultipleFileFilter;
import de.zbit.io.filefilter.SBFileFilter;
import de.zbit.util.prefs.KeyProvider;
import de.zbit.util.prefs.Option;
import de.zbit.util.prefs.OptionGroup;
import de.zbit.util.prefs.Range;
import org.sbml.jsbml.util.ResourceManager;

import java.io.File;
import java.util.ResourceBundle;

/**
 * @author Andreas Dr&auml;ger
 */
public interface EscherIOOptions extends KeyProvider {

  /**
   * Localization support.
   */
  ResourceBundle
                                     bundle   =
      ResourceManager.getBundle("Messages");
  /**
   * Specifies the JSON input file. If a directory is given, the conversion
   * will be recursively performed.
   */
  Option<File>
                                     INPUT    =
      new Option<File>("INPUT", File.class, bundle, new Range<File>(File.class,
          new MultipleFileFilter("JSON", SBFileFilter.createJSONFileFilter(),
              SBFileFilter.createDirectoryFilter())));
  /**
   * The path to the file into which the output should be written. If the
   * input is a directory, this must also be a directory in order to perform a
   * recursive conversion.
   */
  Option<File>
                                     OUTPUT   =
      new Option<File>("OUTPUT", File.class, bundle, new Range<File>(File.class,
          new MultipleFileFilter("SBML, SBGN",
              SBFileFilter.createSBMLFileFilter(),
              SBFileFilter.createSBGNFileFilter(),
              SBFileFilter.createDirectoryFilter())));
  /**
   * Definition of input and output data files as well as the format for the output.
   */
  @SuppressWarnings("unchecked")
                      OptionGroup<?>
                                     GROUP_IO =
      new OptionGroup<Object>("GROUP_IO", bundle, INPUT, OUTPUT);
}
