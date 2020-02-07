package opkeystudio.featurecore.ide.ui.customcontrol.codeeditor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JList;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.wb.swt.ResourceManager;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionCellRenderer;
import org.fife.ui.autocomplete.FunctionCompletion;
import org.fife.ui.autocomplete.MarkupTagCompletion;
import org.fife.ui.autocomplete.TemplateCompletion;
import org.fife.ui.autocomplete.VariableCompletion;

@SuppressWarnings("serial")
public class JavaCellRenderer extends CompletionCellRenderer {

	private BufferedImage convertToAWT(ImageData data) {
		ColorModel colorModel = null;
		PaletteData palette = data.palette;
		if (palette.isDirect) {
			colorModel = new DirectColorModel(data.depth, palette.redMask, palette.greenMask, palette.blueMask);
			BufferedImage bufferedImage = new BufferedImage(colorModel,
					colorModel.createCompatibleWritableRaster(data.width, data.height), false, null);
			WritableRaster raster = bufferedImage.getRaster();
			int[] pixelArray = new int[3];
			for (int y = 0; y < data.height; y++) {
				for (int x = 0; x < data.width; x++) {
					int pixel = data.getPixel(x, y);
					RGB rgb = palette.getRGB(pixel);
					pixelArray[0] = rgb.red;
					pixelArray[1] = rgb.green;
					pixelArray[2] = rgb.blue;
					raster.setPixels(x, y, 1, 1, pixelArray);
				}
			}
			return bufferedImage;
		} else {
			RGB[] rgbs = palette.getRGBs();
			byte[] red = new byte[rgbs.length];
			byte[] green = new byte[rgbs.length];
			byte[] blue = new byte[rgbs.length];
			for (int i = 0; i < rgbs.length; i++) {
				RGB rgb = rgbs[i];
				red[i] = (byte) rgb.red;
				green[i] = (byte) rgb.green;
				blue[i] = (byte) rgb.blue;
			}
			if (data.transparentPixel != -1) {
				colorModel = new IndexColorModel(data.depth, rgbs.length, red, green, blue, data.transparentPixel);
			} else {
				colorModel = new IndexColorModel(data.depth, rgbs.length, red, green, blue);
			}
			BufferedImage bufferedImage = new BufferedImage(colorModel,
					colorModel.createCompatibleWritableRaster(data.width, data.height), false, null);
			WritableRaster raster = bufferedImage.getRaster();
			int[] pixelArray = new int[1];
			for (int y = 0; y < data.height; y++) {
				for (int x = 0; x < data.width; x++) {
					int pixel = data.getPixel(x, y);
					pixelArray[0] = pixel;
					raster.setPixel(x, y, pixelArray);
				}
			}
			return bufferedImage;
		}
	}

	@Override
	protected Icon createEmptyIcon() {

		return super.createEmptyIcon();
	}

	@Override
	public void delegateToSubstanceRenderer() throws Exception {

		super.delegateToSubstanceRenderer();
	}

	@Override
	public DefaultListCellRenderer getDelegateRenderer() {

		return super.getDelegateRenderer();
	}

	@Override
	public Font getDisplayFont() {

		return super.getDisplayFont();
	}

	@Override
	protected Icon getEmptyIcon() {

		return super.getEmptyIcon();
	}

	@Override
	protected Icon getIcon(String arg0) {

		return super.getIcon(arg0);
	}

	@Override
	public Component getListCellRendererComponent(@SuppressWarnings("rawtypes") JList arg0, Object arg1, int arg2, boolean arg3, boolean arg4) {

		return super.getListCellRendererComponent(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public boolean getShowTypes() {

		return super.getShowTypes();
	}

	@Override
	protected void paintComponent(Graphics arg0) {

		super.paintComponent(arg0);
	}

	@Override
	protected void prepareForFunctionCompletion(@SuppressWarnings("rawtypes") JList arg0, FunctionCompletion arg1, int arg2, boolean arg3,
			boolean arg4) {

		super.prepareForFunctionCompletion(arg0, arg1, arg2, arg3, arg4);
		Image img = ResourceManager.getPluginImage("OpKeyStudio", "icons/intellisense/green dot.ico");
		BufferedImage image = convertToAWT(img.getImageData());
		Icon icon = new ImageIcon(image);
		setIcon(icon);
	}

	@Override
	protected void prepareForMarkupTagCompletion(@SuppressWarnings("rawtypes") JList list, MarkupTagCompletion mc, int index, boolean selected,
			boolean hasFocus) {

		super.prepareForMarkupTagCompletion(list, mc, index, selected, hasFocus);
	}

	@Override
	protected void prepareForOtherCompletion(@SuppressWarnings("rawtypes") JList arg0, Completion arg1, int arg2, boolean arg3, boolean arg4) {
		super.prepareForOtherCompletion(arg0, arg1, arg2, arg3, arg4);
		Image img = ResourceManager.getPluginImage("OpKeyStudio", "icons/intellisense/class.ico");
		BufferedImage image = convertToAWT(img.getImageData());
		Icon icon = new ImageIcon(image);
		setIcon(icon);
	}

	@Override
	protected void prepareForTemplateCompletion(@SuppressWarnings("rawtypes") JList list, TemplateCompletion tc, int index, boolean selected,
			boolean hasFocus) {

		super.prepareForTemplateCompletion(list, tc, index, selected, hasFocus);
	}

	@Override
	protected void prepareForVariableCompletion(@SuppressWarnings("rawtypes") JList list, VariableCompletion vc, int index, boolean selected,
			boolean hasFocus) {

		super.prepareForVariableCompletion(list, vc, index, selected, hasFocus);
		Image img = ResourceManager.getPluginImage("OpKeyStudio", "icons/intellisense/yellow dot.ico");
		BufferedImage image = convertToAWT(img.getImageData());
		Icon icon = new ImageIcon(image);
		setIcon(icon);
	}

	@Override
	public void setDelegateRenderer(DefaultListCellRenderer delegate) {

		super.setDelegateRenderer(delegate);
	}

	@Override
	public void setDisplayFont(Font font) {

		super.setDisplayFont(font);
	}

	@Override
	protected void setIconWithDefault(Completion completion, Icon defaultIcon) {

		super.setIconWithDefault(completion, defaultIcon);
	}

	@Override
	protected void setIconWithDefault(Completion completion) {

		super.setIconWithDefault(completion);
	}

	@Override
	public void setParamColor(Color color) {

		super.setParamColor(color);
	}

	@Override
	public void setShowTypes(boolean show) {

		super.setShowTypes(show);
	}

	@Override
	public void setTypeColor(Color color) {

		super.setTypeColor(color);
	}

	@Override
	public void updateUI() {

		super.updateUI();
	}

}
