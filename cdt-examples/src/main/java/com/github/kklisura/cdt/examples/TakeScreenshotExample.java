package com.github.kklisura.cdt.examples;

import com.github.kklisura.cdt.launch.ChromeLauncher;
import com.github.kklisura.cdt.protocol.commands.Page;
import com.github.kklisura.cdt.services.ChromeDevToolsService;
import com.github.kklisura.cdt.services.ChromeService;
import com.github.kklisura.cdt.services.types.ChromeTab;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * Takes a page screenshot.
 *
 * @author Kenan Klisura
 */
public class TakeScreenshotExample {
  public static void main(String[] args) throws InterruptedException {
    // Create chrome launcher.
    final ChromeLauncher launcher = new ChromeLauncher();

    // Launch chrome either as headless (true) or regular (false).
    final ChromeService chromeService = launcher.launch(false);

    // Create empty tab ie about:blank.
    final ChromeTab tab = chromeService.createTab();

    // Get DevTools service to this tab
    final ChromeDevToolsService devToolsService = chromeService.createDevToolsService(tab);

    // Get individual commands
    final Page page = devToolsService.getPage();

    page.onLoadEventFired(
        event -> {
          System.out.println("Taking screenshot...");
          dump("screenshot.png", page.captureScreenshot());
          System.out.println("Done!");

          devToolsService.close();
        });

    // Enable page events.
    page.enable();

    // Navigate to github.com.
    page.navigate("http://github.com");

    devToolsService.waitUntilClosed();
  }

  private static void dump(String fileName, String data) {
    FileOutputStream fileOutputStream = null;
    try {
      File file = new File(fileName);
      fileOutputStream = new FileOutputStream(file);
      fileOutputStream.write(Base64.getDecoder().decode(data));
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (fileOutputStream != null) {
        try {
          fileOutputStream.flush();
          fileOutputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
