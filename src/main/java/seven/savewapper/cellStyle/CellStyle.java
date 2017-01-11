package seven.savewapper.cellStyle;
//=======================================================
//		          .----.
//		       _.'__    `.
//		   .--(^)(^^)---/!\
//		 .' @          /!!!\
//		 :         ,    !!!!
//		  `-..__.-' _.-\!!!/
//		        `;_:    `"'
//		      .'"""""`.
//		     /,  ya ,\\
//		    //狗神保佑\\
//		    `-._______.-'
//		    ___`. | .'___
//		   (______|______)
//=======================================================

import org.apache.poi.ss.usermodel.*;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2017/1/11.
 */
public class CellStyle {

    private CellStyle(org.apache.poi.ss.usermodel.CellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }

    public CellStyle setDataFormat(short fmt) {
        cellStyle.setDataFormat(fmt);
        return this;
    }

    public CellStyle setFont(Font font) {
        cellStyle.setFont(font);
        return this;
    }

    public CellStyle setHidden(boolean hidden) {
        cellStyle.setHidden(hidden);
        return this;
    }

    public CellStyle setLocked(boolean locked) {
        cellStyle.setLocked(locked);
        return this;
    }

    public CellStyle setAlignment(HorizontalAlignment align) {
        cellStyle.setAlignment(align);
        return this;
    }

    public CellStyle setWrapText(boolean wrapped) {
        cellStyle.setWrapText(wrapped);
        return this;
    }

    public CellStyle setVerticalAlignment(VerticalAlignment align) {
        cellStyle.setVerticalAlignment(align);
        return this;
    }

    public CellStyle setRotation(short rotation) {
        cellStyle.setRotation(rotation);
        return this;
    }

    public CellStyle setIndention(short indent) {
        cellStyle.setIndention(indent);
        return this;
    }

    public CellStyle setBorderLeft(BorderStyle border) {
        cellStyle.setBorderLeft(border);
        return this;
    }

    public CellStyle setBorderRight(BorderStyle border) {
        cellStyle.setBorderRight(border);
        return this;
    }

    public CellStyle setBorderTop(BorderStyle border) {
        cellStyle.setBorderTop(border);
        return this;
    }

    public CellStyle setBorderBottom(BorderStyle border) {
        cellStyle.setBorderBottom(border);
        return this;
    }

    public CellStyle setLeftBorderColor(short color) {
        cellStyle.setLeftBorderColor(color);
        return this;
    }

    public CellStyle setRightBorderColor(short color) {
        cellStyle.setRightBorderColor(color);
        return this;
    }

    public CellStyle setTopBorderColor(short color) {
        cellStyle.setTopBorderColor(color);
        return this;
    }

    public CellStyle setBottomBorderColor(short color) {
        cellStyle.setBottomBorderColor(color);
        return this;
    }

    public CellStyle setFillPattern(FillPatternType fp) {
        cellStyle.setFillPattern(fp);
        return this;
    }

    public CellStyle setFillBackgroundColor(short bg) {
        cellStyle.setFillBackgroundColor(bg);
        return this;
    }

    public CellStyle setFillForegroundColor(short bg) {
        cellStyle.setFillForegroundColor(bg);
        return this;
    }

    public CellStyle cloneStyleFrom(org.apache.poi.ss.usermodel.CellStyle source) {
        cellStyle.cloneStyleFrom(source);
        return this;
    }

    public CellStyle setShrinkToFit(boolean shrinkToFit) {
        cellStyle.setShrinkToFit(shrinkToFit);
        return this;
    }

    public org.apache.poi.ss.usermodel.CellStyle getRealyStyle() {
        return this.cellStyle;
    }

    private org.apache.poi.ss.usermodel.CellStyle cellStyle;
    public static final CellStyle CreateStyle(org.apache.poi.ss.usermodel.CellStyle cellStyle ) {
        return new CellStyle(cellStyle).setFillPattern(FillPatternType.BRICKS);
    }
}
