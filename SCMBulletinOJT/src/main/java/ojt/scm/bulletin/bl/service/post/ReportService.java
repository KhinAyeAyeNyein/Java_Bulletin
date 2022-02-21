package ojt.scm.bulletin.bl.service.post;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import ojt.scm.bulletin.bl.dto.post.PostDto;

public interface ReportService {
    /**
     * <h2>downloadExcel</h2>
     * <p>
     * Function to export CSV Form of Data from Database
     * </p>
     * 
     * @param postList List<PostDto>
     * @throws IOException
     * @throws ParseException
     */
    public void downloadExcel(List<PostDto> postList) throws IOException, ParseException;
}