package pcloudystudio.mobilespy.dialog;

import org.eclipse.jface.resource.ImageDescriptor;
import java.io.File;
import java.util.Map;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import opkeystudio.iconManager.OpKeyStudioIcons;
import pcloudystudio.objectspy.element.MobileElement;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.dialogs.Dialog;

public class MobileDeviceDialog extends Dialog {
	private static final String DIALOG_TITLE = "Mobile Device";
	private Image currentScreenShot = ResourceManager.getPluginImage("OpKeyStudio",
			OpKeyStudioIcons.MOBILE_SPY_CAPTURED_IMAGE);
	private Canvas canvas;
	public static final int DIALOG_WIDTH = 400;
	public static final int DIALOG_HEIGHT = 600;
	private double currentX;
	private double currentY;
	private double currentWidth;
	private double currentHeight;
	private double hRatio;
	private boolean isDisposed;
	private Point initialLocation;
	private MobileElementInspectorDialog mobileInspetorDialog;
	private ScrolledComposite scrolledComposite;

	public MobileDeviceDialog(final Shell parentShell, final MobileElementInspectorDialog mobileInspectorDialog,
			final Point location) {
		super(parentShell);
		this.currentX = 0.0;
		this.currentY = 0.0;
		this.currentWidth = 0.0;
		this.currentHeight = 0.0;
		this.mobileInspetorDialog = mobileInspectorDialog;
		this.initialLocation = location;
		this.setShellStyle(SWT.CLOSE | SWT.MODELESS | SWT.BORDER | SWT.TITLE);
		this.isDisposed = false;
	}

	protected Control createDialogArea(final Composite parent) {
		final Composite dialogArea = (Composite) super.createDialogArea(parent);
		final GridLayout dialogAreaGridLayout = (GridLayout) dialogArea.getLayout();
		dialogAreaGridLayout.marginWidth = 0;
		dialogAreaGridLayout.marginHeight = 0;
		(this.scrolledComposite = (ScrolledComposite) new ScrollableComposite(dialogArea,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL)).setExpandHorizontal(true);
		this.scrolledComposite.setExpandVertical(true);
		this.scrolledComposite.setLayout((Layout) new GridLayout());
		this.scrolledComposite.setLayoutData((Object) new GridData(4, 4, true, true));
		final Composite container = new Composite((Composite) this.scrolledComposite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		container.setLayout((Layout) new FillLayout());
		this.scrolledComposite.setContent((Control) container);
		
		this.currentWidth = this.currentScreenShot.getImageData().width;
		this.currentHeight  = this.currentScreenShot.getImageData().height;
		
		(this.canvas = new Canvas(container, 0)).pack();
		this.canvas.addPaintListener((PaintListener) new PaintListener() {
			public void paintControl(final PaintEvent e) {
				if (MobileDeviceDialog.this.currentScreenShot != null
						&& !MobileDeviceDialog.this.currentScreenShot.isDisposed()) {
					e.gc.drawImage(MobileDeviceDialog.this.currentScreenShot, 0, 0);
					if (MobileDeviceDialog.this.currentWidth != 0.0 && MobileDeviceDialog.this.currentHeight != 0.0) {
						final Color oldForegroundColor = e.gc.getForeground();
						e.gc.setForeground(new Color((Device) Display.getCurrent(), new RGB(118, 198, 66)));
						final int x = MobileDeviceDialog.safeRoundDouble(MobileDeviceDialog.this.currentX);
						final int y = MobileDeviceDialog.safeRoundDouble(MobileDeviceDialog.this.currentY);
						final int width = MobileDeviceDialog.safeRoundDouble(MobileDeviceDialog.this.currentWidth);
						final int height = MobileDeviceDialog.safeRoundDouble(MobileDeviceDialog.this.currentHeight);
						e.gc.drawRectangle(x, y, width, height);
						e.gc.drawRectangle(x + 1, Math.max(y + 1, 0), Math.max(width - 2, 0), Math.max(height - 2, 0));
						e.gc.setForeground(oldForegroundColor);
					}
				}
			}
		});

		this.canvas.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				if (e.button == 1) {
					MobileDeviceDialog.this.inspectElementAt(e.x, e.y);
					System.out.println("e.x and e.y: "+e.x +" "+ e.y);
				}
			}
		});
		return (Control) dialogArea;
	}

	protected boolean isResizable() {
		return true;
	}

	private void inspectElementAt(final int x, final int y) {
		final Double realX = x / this.hRatio;
		final Double realY = y / this.hRatio;
		mobileInspetorDialog.setSelectedElementByLocation(safeRoundDouble(realX), safeRoundDouble(realY));
	}

	private boolean isElementOnScreen(final Double x, final Double y, final Double width, final Double height) {
		final Rectangle elementRect = new Rectangle(x.intValue(), y.intValue(), width.intValue(), height.intValue());
		return elementRect.intersects(this.getCurrentViewportRect());
	}

	private void scrollToElement(final Double x, final Double y) {
		this.scrolledComposite.setOrigin(x.intValue(), y.intValue());
	}

	private Rectangle getCurrentViewportRect() {
		final ScrollBar verticalBar = this.scrolledComposite.getVerticalBar();
		final ScrollBar horizontalBar = this.scrolledComposite.getHorizontalBar();
		final int viewPortY = verticalBar.isVisible() ? verticalBar.getSelection() : 0;
		final int viewPortX = horizontalBar.isVisible() ? horizontalBar.getSelection() : 0;
		final Point viewPortSize = this.scrolledComposite.getSize();
		final Rectangle viewPortRect = new Rectangle(viewPortX, viewPortY, viewPortSize.x, viewPortSize.y);
		return viewPortRect;
	}

	public void highlight(final double x, final double y, final double width, final double height) {
		Display.getCurrent().syncExec((Runnable) new Runnable() {
			@Override
			public void run() {
				final double currentX = x * MobileDeviceDialog.this.hRatio;
				final double currentY = y * MobileDeviceDialog.this.hRatio;
				final double currentWidth = width * MobileDeviceDialog.this.hRatio;
				final double currentHeight = height * MobileDeviceDialog.this.hRatio;
				if (!MobileDeviceDialog.this.isElementOnScreen(currentX, currentY, currentWidth, currentHeight)) {
					MobileDeviceDialog.this.scrollToElement(currentX, currentY);
				}
			}
		});
		final Thread highlightThread = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 9; ++i) {
					if (i % 2 == 1) {
						MobileDeviceDialog.setCurrentX(MobileDeviceDialog.this, x * MobileDeviceDialog.this.hRatio);
						MobileDeviceDialog.setCurrentY(MobileDeviceDialog.this, y * MobileDeviceDialog.this.hRatio);
						MobileDeviceDialog.setCurrentWidth(MobileDeviceDialog.this,
								width * MobileDeviceDialog.this.hRatio);
						MobileDeviceDialog.setCurrentHeight(MobileDeviceDialog.this,
								height * MobileDeviceDialog.this.hRatio);
					} else {
						MobileDeviceDialog.setCurrentX(MobileDeviceDialog.this, 0.0);
						MobileDeviceDialog.setCurrentY(MobileDeviceDialog.this, 0.0);
						MobileDeviceDialog.setCurrentWidth(MobileDeviceDialog.this, 0.0);
						MobileDeviceDialog.setCurrentHeight(MobileDeviceDialog.this, 0.0);
					}
					try {
						Thread.sleep(200L);
					} catch (InterruptedException ex) {
					}
					getParentShell().getDisplay().syncExec(() -> {
						if (!MobileDeviceDialog.this.canvas.isDisposed()) {
							MobileDeviceDialog.this.canvas.redraw();
						}
						return;
					});
				}
			}
		});
		highlightThread.start();
	}

	private Image scaleImage(final Image image, final double newWidth, final double newHeight) {
		final Image scaled = new Image((Device) Display.getDefault(), safeRoundDouble(newWidth),
				safeRoundDouble(newHeight));
		final GC gc = new GC((Drawable) scaled);
		gc.setAntialias(1);
		gc.setInterpolation(2);
		gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, 0, 0, safeRoundDouble(newWidth),
				safeRoundDouble(newHeight));
		gc.dispose();
		image.dispose();
		return scaled;
	}

	protected Point getInitialSize() {
		return new Point(400, 657);
	}

	protected void configureShell(final Shell shell) {
		super.configureShell(shell);
		shell.setText("Mobile Device");
	}

	protected void setShellStyle(final int newShellStyle) {
		super.setShellStyle(SWT.CLOSE | SWT.MODELESS | SWT.BORDER | SWT.TITLE);
		this.setBlockOnOpen(false);
	}

	protected Control createButtonBar(final Composite parent) {
		return (Control) parent;
	}

	public void closeApp() {
		this.handleShellCloseEvent();
	}

	public void highlightElement(final MobileElement selectedElement) {
		final Map<String, String> attributes = selectedElement.getAttributes();

		if (attributes == null || !attributes.containsKey("x") || !attributes.containsKey("y")
				|| !attributes.containsKey("width") || !attributes.containsKey("height")) {
			System.out.println(attributes.get("xpath"));
			System.out.println(attributes.get("x"));
			System.out.println(attributes.get("y"));
			System.out.println(attributes.get("width"));
			System.out.println(attributes.get("height"));
			return;
		}
		final double x = Double.parseDouble(attributes.get("x"));
		final double y = Double.parseDouble(attributes.get("y"));
		final double w = Double.parseDouble(attributes.get("width"));
		final double h = Double.parseDouble(attributes.get("height"));
		this.highlight(x, y, w, h);
	}

	/*
	 * public void refreshDialog(final File imageFile, final MobileElement root) {
	 * System.out.println("AAAAAAAA"); try { final ImageDescriptor imgDesc =
	 * ImageDescriptor.createFromURL(imageFile.toURI().toURL()); final Image img =
	 * imgDesc.createImage(); final Map<String, String> attributes; final Image
	 * image; double rootHeight; final double imageRatio;
	 * this.getParentShell().getDisplay().syncExec(() -> { attributes =
	 * root.getAttributes(); rootHeight = image.getBounds().height;
	 * System.out.println(rootHeight); if (attributes.containsKey("height")) {
	 * rootHeight = Double.parseDouble(attributes.get("height"));
	 * 
	 * } imageRatio = rootHeight / image.getBounds().height; this.hRatio =
	 * this.canvas.getSize().y / rootHeight; this.currentScreenShot =
	 * this.scaleImage(image, image.getBounds().width * imageRatio * this.hRatio,
	 * image.getBounds().height * this.hRatio * imageRatio); this.canvas.redraw();
	 * return; }); this.refreshView(); } catch (Exception ex) {
	 * LoggerSingleton.logError((Throwable) ex); } }
	 */
	private void refreshView() {
		if (this.scrolledComposite == null || this.currentScreenShot == null) {
			return;
		}
		Display.getDefault().asyncExec((Runnable) new Runnable() {
			@Override
			public void run() {
				MobileDeviceDialog.this.scrolledComposite.setMinSize(
						MobileDeviceDialog.this.currentScreenShot.getImageData().width,
						MobileDeviceDialog.this.currentScreenShot.getImageData().height);
			}
		});
	}

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

	protected Point getInitialLocation(final Point initialSize) {
		if ((this.getShell().getStyle() & 0x10) == 0x0) {
			return new Point(this.initialLocation.x, this.initialLocation.y + 5);
		}
		return this.initialLocation;
	}

	public static int safeRoundDouble(final double d) {
		final long rounded = Math.round(d);
		return (int) Math.max(-2147483648L, Math.min(2147483647L, rounded));
	}

	static void setCurrentX(final MobileDeviceDialog mobileDeviceDialog, final double currentX) {
		mobileDeviceDialog.currentX = currentX;
	}

	static void setCurrentY(final MobileDeviceDialog mobileDeviceDialog, final double currentY) {
		mobileDeviceDialog.currentY = currentY;
	}

	static void setCurrentWidth(final MobileDeviceDialog mobileDeviceDialog, final double currentWidth) {
		mobileDeviceDialog.currentWidth = currentWidth;
	}

	static void setCurrentHeight(final MobileDeviceDialog mobileDeviceDialog, final double currentHeight) {
		mobileDeviceDialog.currentHeight = currentHeight;
	}
}
