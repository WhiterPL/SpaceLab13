package io.github.whiterpl.sl13.gui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class InfoPane extends Table {

    protected Label statLabel;
    protected List inventoryList;

    public InfoPane(BitmapFont font, NinePatchDrawable border) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        statLabel = new Label("Character stats", labelStyle);
        statLabel.setFontScale(0.5F);

        List.ListStyle listStyle = new List.ListStyle();
        listStyle.font = font;
        listStyle.background = border;
        listStyle.selection = border;
        inventoryList = new List<Label>(listStyle);

        this.background(border);
        this.add(statLabel).expand().top().left().pad(5);
        this.row();
        this.add(inventoryList).expand().top().left().pad(5);
    }
}
