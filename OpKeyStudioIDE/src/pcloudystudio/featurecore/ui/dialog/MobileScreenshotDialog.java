package pcloudystudio.featurecore.ui.dialog;

//Created by Alok Gupta on 20/02/2020.
//Copyright © 2020 SSTS Inc. All rights reserved.

import java.io.File;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Shell;

import pcloudystudio.resources.constant.ImageConstants;
import pcloudystudio.spytreecomponents.MobileElement;

public class MobileScreenshotDialog extends Dialog {
	private static String DIALOG_TITLE = "Mobile Screenshot";
	private Image currentScreenShot;
	private Canvas canvas;
	public static int DIALOG_WIDTH = 400;
	public static int DIALOG_HEIGHT = 600;
	private double currentX;
	private double currentY;
	private double currentWidth;
	private double currentHeight;
	private double hRatio;
	private boolean isDisposed;
	private Point initialLocation;
	private MobileElementInspectorDialog mobileInspetorDialog;
	private ScrolledComposite scrolledComposite;

	public MobileScreenshotDialog(Shell parentShell, MobileElementInspectorDialog mobileInspectorDialog,
			Point location) {
		super(parentShell);
		this.currentX = 0.0;
		this.currentY = 0.0;
		this.currentWidth = 0.0;
		this.currentHeight = 0.0;
		this.mobileInspetorDialog = mobileInspectorDialog;
		this.initialLocation = location;
		this.setShellStyle(SWT.CLOSE | SWT.TITLE);
		this.isDisposed = false;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialogArea = (Composite) super.createDialogArea(parent);
		GridLayout dialogAreaGridLayout = (GridLayout) dialogArea.getLayout();
		dialogAreaGridLayout.marginWidth = 0;
		dialogAreaGridLayout.marginHeight = 0;
		(this.scrolledComposite = new ScrollableComposite(dialogArea, SWT.H_SCROLL | SWT.V_SCROLL))
		.setExpandHorizontal(true);
		this.scrolledComposite.setExpandVertical(true);
		this.scrolledComposite.setLayout((Layout) new GridLayout());
		this.scrolledComposite.setLayoutData((Object) new GridData(4, 4, true, true));
		Composite container = new Composite((Composite) this.scrolledComposite, SWT.BORDER);
		container.setLayout((Layout) new FillLayout());
		this.scrolledComposite.setContent((Control) container);

		(this.canvas = new Canvas(container, SWT.NONE)).pack();
		this.canvas.addPaintListener((PaintListener) new PaintListener() {
			public void paintControl(PaintEvent e) {
				if (MobileScreenshotDialog.this.currentScreenShot != null
						&& !MobileScreenshotDialog.this.currentScreenShot.isDisposed()) {
					e.gc.drawImage(MobileScreenshotDialog.this.currentScreenShot, 0, 0);
					if (MobileScreenshotDialog.this.currentWidth != 0.0
							&& MobileScreenshotDialog.this.currentHeight != 0.0) {
						Color oldForegroundColor = e.gc.getForeground();
						e.gc.setForeground(new Color((Device) Display.getCurrent(), new RGB(118, 198, 66)));
						int x = MobileScreenshotDialog.safeRoundDouble(MobileScreenshotDialog.this.currentX);
						int y = MobileScreenshotDialog.safeRoundDouble(MobileScreenshotDialog.this.currentY);
						int width = MobileScreenshotDialog.safeRoundDouble(MobileScreenshotDialog.this.currentWidth);
						int height = MobileScreenshotDialog.safeRoundDouble(MobileScreenshotDialog.this.currentHeight);
						e.gc.drawRectangle(x, y, width, height);
						e.gc.drawRectangle(x + 1, Math.max(y + 1, 0), Math.max(width - 2, 0), Math.max(height - 2, 0));
						e.gc.setForeground(oldForegroundColor);
					}
				}
			}
		});

		this.canvas.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				if (e.button == 1) {
					MobileScreenshotDialog.this.inspectElementAt(e.x, e.y);
				}
			}
		});

		return (Control) dialogArea;
	}

	/*--------------------------*/

	@Override
	protected boolean isResizable() {
		return true;
	}

	private void inspectElementAt(int x, int y) {
		Double realX = x / this.hRatio;
		Double realY = y / this.hRatio;
		this.mobileInspetorDialog.setSelectedElementByLocation(safeRoundDouble(realX), safeRoundDouble(realY));
	}

	private boolean isElementOnScreen(Double x, Double y, Double width, Double height) {
		Rectangle elementRect = new Rectangle(x.intValue(), y.intValue(), width.intValue(), height.intValue());
		return elementRect.intersects(this.getCurrentViewportRect());
	}

	private void scrollToElement(Double x, Double y) {
		this.scrolledComposite.setOrigin(x.intValue(), y.intValue());
	}

	private Rectangle getCurrentViewportRect() {
		ScrollBar verticalBar = this.scrolledComposite.getVerticalBar();
		ScrollBar horizontalBar = this.scrolledComposite.getHorizontalBar();
		int viewPortY = verticalBar.isVisible() ? verticalBar.getSelection() : 0;
		int viewPortX = horizontalBar.isVisible() ? horizontalBar.getSelection() : 0;
		Point viewPortSize = this.scrolledComposite.getSize();
		Rectangle viewPortRect = new Rectangle(viewPortX, viewPortY, viewPortSize.x, viewPortSize.y);
		return viewPortRect;
	}

	public void highlight(double x, double y, double width, double height) {
		Display.getCurrent().syncExec((Runnable) new Runnable() {
			@Override
			public void run() {
				double currentX = x * MobileScreenshotDialog.this.hRatio;
				double currentY = y * MobileScreenshotDialog.this.hRatio;
				double currentWidth = width * MobileScreenshotDialog.this.hRatio;
				double currentHeight = height * MobileScreenshotDialog.this.hRatio;
				if (!MobileScreenshotDialog.this.isElementOnScreen(currentX, currentY, currentWidth, currentHeight)) {
					MobileScreenshotDialog.this.scrollToElement(currentX, currentY);
				}
			}
		});
		Thread highlightThread = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 9; ++i) {
					if (i % 2 == 1) {
						MobileScreenshotDialog.setCurrentX(MobileScreenshotDialog.this,
								x * MobileScreenshotDialog.this.hRatio);
						MobileScreenshotDialog.setCurrentY(MobileScreenshotDialog.this,
								y * MobileScreenshotDialog.this.hRatio);
						MobileScreenshotDialog.setCurrentWidth(MobileScreenshotDialog.this,
								width * MobileScreenshotDialog.this.hRatio);
						MobileScreenshotDialog.setCurrentHeight(MobileScreenshotDialog.this,
								height * MobileScreenshotDialog.this.hRatio);
					} else {
						MobileScreenshotDialog.setCurrentX(MobileScreenshotDialog.this, 0.0);
						MobileScreenshotDialog.setCurrentY(MobileScreenshotDialog.this, 0.0);
						MobileScreenshotDialog.setCurrentWidth(MobileScreenshotDialog.this, 0.0);
						MobileScreenshotDialog.setCurrentHeight(MobileScreenshotDialog.this, 0.0);
					}
					try {
						Thread.sleep(200L);
					} catch (InterruptedException ex) {
					}
					Display.getDefault().syncExec(() -> {
						if (!MobileScreenshotDialog.this.canvas.isDisposed()) {
							MobileScreenshotDialog.this.canvas.redraw();
						}
						return;
					});
				}
			}
		});
		highlightThread.start();
	}

	private Image scaleImage(Image image, double newWidth, double newHeight) {
		Image scaled = new Image((Device) Display.getDefault(), safeRoundDouble(newWidth), safeRoundDouble(newHeight));
		GC gc = new GC((Drawable) scaled);
		gc.setAntialias(1);
		gc.setInterpolation(2);
		gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, 0, 0, safeRoundDouble(newWidth),
				safeRoundDouble(newHeight));
		gc.dispose();
		image.dispose();
		return scaled;
	}

	@Override
	protected Point getInitialSize() {
		return new Point(DIALOG_WIDTH, DIALOG_HEIGHT);
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(DIALOG_TITLE);
		shell.setImage(ImageConstants.IMG_16_OPKEY_LOGO);
	}

	@Override
	protected void setShellStyle(int newShellStyle) {
		super.setShellStyle(SWT.BORDER | SWT.TITLE | SWT.CLOSE); // 2160
		this.setBlockOnOpen(false);
	}

	@Override
	protected Control createButtonBar(Composite parent) {
		return (Control) parent;
	}

	public void closeApp() {
		this.handleShellCloseEvent();
	}

	public void highlightElement(MobileElement selectedElement) {
		Map<String, String> attributes = selectedElement.getAttributes();
		if (attributes == null || !attributes.containsKey("x") || !attributes.containsKey("y")
				|| !attributes.containsKey("width") || !attributes.containsKey("height")) {
			return;
		}
		double x = Double.parseDouble(attributes.get("x"));
		double y = Double.parseDouble(attributes.get("y"));
		double w = Double.parseDouble(attributes.get("width"));
		double h = Double.parseDouble(attributes.get("height"));
		this.highlight(x, y, w, h);
	}

	public void refreshDialog(File imageFile, MobileElement root) {
		try {
			ImageDescriptor imgDesc = ImageDescriptor.createFromURL(imageFile.toURI().toURL());
			Image img = imgDesc.createImage();
			this.getShell().getDisplay().syncExec(() -> {
				Map<String, String> attributes = root.getAttributes();
				double rootHeight = img.getBounds().height;
				if (attributes.containsKey("height")) {
					rootHeight = Double.parseDouble(attributes.get("height"));
				}
				double imageRatio = rootHeight / img.getBounds().height;
				this.hRatio = this.canvas.getSize().y / rootHeight;
				this.currentScreenShot = this.scaleImage(img, img.getBounds().width * imageRatio * this.hRatio,
						img.getBounds().height * this.hRatio * imageRatio);
				this.canvas.redraw();
				return;
			});
			this.refreshView();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void refreshView() {
		if (this.scrolledComposite == null || this.currentScreenShot == null) {
			return;
		}
		Display.getDefault().asyncExec((Runnable) new Runnable() {
			@Override
			public void run() {
				MobileScreenshotDialog.this.scrolledComposite.setMinSize(
						MobileScreenshotDialog.this.currentScreenShot.getImageData().width,
						MobileScreenshotDialog.this.currentScreenShot.getImageData().height);
			}
		});
	}

	@Override
	protected void handleShellCloseEvent() {
		super.handleShellCloseEvent();
		this.dispose();
	}

	public void dispose() {
		this.isDisposed = true;
	}

	public boolean isDisposed() {
		return this.isDisposed;
	}

	@Override
	protected Point getInitialLocation(Point initialSize) {
		if ((this.getShell().getStyle() & 0x10) == 0x0) {
			return new Point(this.initialLocation.x, this.initialLocation.y + 5);
		}
		return this.initialLocation;
	}

	public static int safeRoundDouble(double d) {
		long rounded = Math.round(d);
		return (int) Math.max(-2147483648L, Math.min(2147483647L, rounded));
	}

	static void setCurrentX(MobileScreenshotDialog mobileDeviceDialog, double currentX) {
		mobileDeviceDialog.currentX = currentX;
	}

	static void setCurrentY(MobileScreenshotDialog mobileDeviceDialog, double currentY) {
		mobileDeviceDialog.currentY = currentY;
	}

	static void setCurrentWidth(MobileScreenshotDialog mobileDeviceDialog, double currentWidth) {
		mobileDeviceDialog.currentWidth = currentWidth;
	}

	static void setCurrentHeight(MobileScreenshotDialog mobileDeviceDialog, double currentHeight) {
		mobileDeviceDialog.currentHeight = currentHeight;
	}
}
