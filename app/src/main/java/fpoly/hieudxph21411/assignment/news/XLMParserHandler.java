package fpoly.hieudxph21411.assignment.news;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import fpoly.hieudxph21411.assignment.model.TinTuc;

public class XLMParserHandler extends DefaultHandler {
    private ArrayList<TinTuc> itemList;
    private TinTuc tinTuc;
    private boolean isRead = false;
    private String temp;
//    private String ulrImg;

    public List<TinTuc> getItemList() {
//        nếu List để là private thì phải insert getter setter thì mới gọi ở bên ngoài đc

        return itemList;
    }

    public void setItemList(ArrayList<TinTuc> itemList) {
        this.itemList = itemList;
    }

    public XLMParserHandler() {
// ở đây khi nào khởi tạo đối tượng thì mới cấp bộ nhớ
        itemList = new ArrayList<>();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
//      sẽ lấy dữ liệu trong cặp thẻ mở và đóng
        if (isRead) { // nếu vẫn đang đọc
            temp = new String(ch, start, length); // chuyển dữ liệu về dạng string
        }

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
//        tại đây xử lý khi gặp thẻ mở
        if (qName.equalsIgnoreCase("item")) { //k phân biệt hoa thường
            tinTuc = new TinTuc();
            isRead = true;

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
//        tại đây xử lý khi gặp thẻ đóng
//        if (qName.equalsIgnoreCase("url")){
//            ulrImg = temp;
//        }
        if (qName.equalsIgnoreCase("item")) {
//            news.setUrlImg(ulrImg);
            itemList.add(tinTuc);
            isRead = false;
        } else if (isRead) { // nếu read vẫn là true nghĩa là vẫn đang đọc
            if (qName.equalsIgnoreCase("title")) { // nếu gặp thẻ mở title
                tinTuc.setTitle(temp); // temp đc gán ở hàm characters
            } else if (qName.equalsIgnoreCase("description")) {

                String des, img = null;
                int indexS = temp.indexOf("src=\"") + 5; // tìm vị trí của chuỗi src=" trong temp, nếu có thì cộng thêm 5 để lấy vị trí bắt đầu của đường dẫn ảnh,
                int indexE = temp.indexOf(" ", indexS) - 1; //  tìm vị trí kết thúc của đường dẫn ảnh bằng cách tìm khoảng trắng đầu tiên sau vị trí bắt đầu
                if (temp.contains("src=\"")) { // ktra xem temp có chứa chuỗi src= hay k,
                    img = temp.substring(indexS, indexE);
                    des = temp.substring(temp.indexOf("</br>") + 5); //  Nếu có, sẽ lấy nội dung từ vị trí sau chuỗi “</br>”
                } else {
                    des = temp; //  Nếu k có đường dẫn hình trong chuỗi “temp”, sẽ chỉ lấy nội dung của thẻ và gán giá trị cho biến “des”
                }
                tinTuc.setImg(img);
                tinTuc.setDesc(des);

            } else if (qName.equalsIgnoreCase("link")) {
                tinTuc.setLink(temp);
            }

        }
    }


}
