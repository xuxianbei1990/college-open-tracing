package college.mall.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.math.RoundingMode;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

@Service
public class DrawService {
    public void drawPoster(MultipartFile topFile, MultipartFile smallFile, MultipartFile orCodeFile, HttpServletResponse response,
                           DrawPosterDto drawPosterDto) throws Exception {
        int width = 556;
        int height = 848;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);


        int targetWidth = 556; // 目标宽度
        int targetHeight = 556; // 目标高度

        BufferedImage originalImage = ImageIO.read(topFile.getInputStream());
        g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int smallImgWidth = 17;
        int smallImgHeight = 572;

        originalImage = ImageIO.read(smallFile.getInputStream());
        g2d.drawImage(originalImage, smallImgWidth, smallImgHeight, smallImgWidth + 83, 83, null);

        InputStream is = new ClassPathResource("simhei.ttf").getInputStream();
        Font font = Font.createFont(Font.TRUETYPE_FONT, is);
        Font titleFont = font.deriveFont(Font.BOLD, 24f);
        g2d.setFont(titleFont);
        g2d.setColor(Color.BLACK);

        int merchantTitleWidth = 109 + 20;
        int merchantTitleHeight = 572 + 30;

        g2d.drawString(drawPosterDto.getMerchantName(), merchantTitleWidth, merchantTitleHeight);

        Font contentFont = font.deriveFont(Font.PLAIN, 26f);
        g2d.setFont(contentFont);

        int merchantContentWidth = merchantTitleWidth;
        int merchantContentHeight = merchantTitleHeight + 40;

        g2d.drawString(drawPosterDto.getMerchantContext(), merchantContentWidth, merchantContentHeight);

        int moneyWidth = smallImgWidth;
        int moneyHeight = merchantContentHeight + 90;
        Font moneyFont = font.deriveFont(Font.BOLD, 60f);
        g2d.setFont(moneyFont);
        g2d.setColor(Color.RED);
        g2d.drawString(String.valueOf(drawPosterDto.getAmount().intValue()), moneyWidth, moneyHeight);

        int moneyDropWidth = g2d.getFontMetrics().stringWidth("24") + 20;
        int moneyDropHeight = moneyHeight;
        Font moneyDropFont = font.deriveFont(Font.PLAIN, 40f);
        g2d.setFont(moneyDropFont);
        g2d.setColor(Color.RED);
        String[] value = drawPosterDto.getAmount().setScale(2, RoundingMode.HALF_UP).toPlainString().split(".");
        if (value.length > 1) {
            g2d.drawString("." + value[1] + "元起", moneyDropWidth, moneyDropHeight);
        } else {
            g2d.drawString(".00元起", moneyDropWidth, moneyDropHeight);
        }

        int idetifyWidth = g2d.getFontMetrics().stringWidth(".00元起") + 150;
        int idetifyHeight = moneyDropHeight;
        Font identifyFont = font.deriveFont(Font.PLAIN, 30f);
        g2d.setFont(identifyFont);
        g2d.setColor(Color.decode("#4E4E4E"));
        g2d.drawString("长按识别", idetifyWidth, idetifyHeight);

        int orCodeWidth = idetifyWidth + g2d.getFontMetrics().stringWidth("长按识别") + 10;
        int orCodeHeight = merchantContentHeight + 20;

        originalImage = ImageIO.read(orCodeFile.getInputStream());
        g2d.drawImage(originalImage, orCodeWidth, orCodeHeight, 115, 115, null);

        int iconWidth = moneyWidth;
        int conHeight = moneyHeight + 30;

        originalImage = ImageIO.read(new ClassPathResource("Group 2249@3x.png").getInputStream());
        g2d.drawImage(originalImage, iconWidth, conHeight, null);
        iconWidth = iconWidth + originalImage.getWidth();

        int lineWidth = iconWidth + 10;
        int lineHeight = conHeight + 25;
        Font lineFont = font.deriveFont(Font.PLAIN, 30f);
        g2d.setFont(lineFont);
        g2d.setColor(Color.decode("#B2B2B2"));
        g2d.drawLine(lineWidth, lineHeight, lineWidth + 335, lineHeight);

        int lineTextWidth = lineWidth;
        int lineTextHeight = lineHeight + 25;
        Font lineTextFont = font.deriveFont(Font.PLAIN, 25f);
        g2d.setFont(lineTextFont);
        g2d.setColor(Color.decode("#808080"));

        AttributedString attributedText = new AttributedString("中国团购圈 400万团长同步拼团 200个城市同步拼车");

        attributedText.addAttribute(TextAttribute.TRACKING, 0.2f, 0, attributedText.getIterator().getEndIndex());

        AttributedCharacterIterator iterator = attributedText.getIterator();

        g2d.drawString(iterator, lineTextWidth, lineTextHeight);

        g2d.dispose();

        ImageIO.write(image, "png", response.getOutputStream());
    }
}
