/*
 * Copyright (c) 2000-2017 TeamDev Ltd. All rights reserved.
 * Use is subject to Apache 2.0 license terms.
 */
package pl.stacje.test;

import javax.swing.*;

public interface ControlPanel {
    JComponent getControlPanel();

    void configureControlPanel();

    int getPreferredHeight();
}
