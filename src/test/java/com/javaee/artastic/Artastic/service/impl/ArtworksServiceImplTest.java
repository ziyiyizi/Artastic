package com.javaee.artastic.Artastic.service.impl; 

import com.csvreader.CsvReader;
import com.javaee.artastic.Artastic.App;
import com.javaee.artastic.Artastic.service.ArtworksService;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.thymeleaf.context.Context;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/** 
* ArtworksServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 1, 2019</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class ArtworksServiceImplTest {
    @Autowired
    ArtworksService artworksService;

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: findByArtistId(int artistId) 
* 
*/ 
@Test
public void testFindByArtistId() throws Exception { 
//TODO: Test goes here...
    System.out.println("test");
} 

/** 
* 
* Method: findByArtworkId(int artworkId) 
* 
*/ 
@Test
public void testFindByArtworkId() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findByArtworkName(String artworkName) 
* 
*/ 
@Test
public void testFindByArtworkName() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findLikesList(int artworkId) 
* 
*/ 
@Test
public void testFindLikesListArtworkId() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findLikesList(int artworkId, Pageable pageable) 
* 
*/ 
@Test
public void testFindLikesListForArtworkIdPageable() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: countLikes(int artworkId) 
* 
*/ 
@Test
public void testCountLikes() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findCommentList(int artworkId) 
* 
*/ 
@Test
public void testFindCommentList() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findTagList(int artworkId) 
* 
*/ 
@Test
public void testFindTagList() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getArtworkDetails(int artworkId, int clientId) 
* 
*/ 
@Test
public void testGetArtworkDetailsForArtworkIdClientId() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getArtworkDetails(int artworkId) 
* 
*/ 
@Test
public void testGetArtworkDetailsArtworkId() throws Exception { 
//TODO: Test goes here...
    String testCase = "UT_TC_002_001";
    System.out.println(testCase);

    CsvReader csvReader = new CsvReader("src/testcase/unittest/UT_TC_002_001.csv", ',', Charset.forName("utf8"));
    csvReader.readHeaders();
    System.out.println("Test-function--testGetArtworkDetailsArtworkId");
    //用来存储是否通过测试的Boolean
    List<Boolean> passedList = new ArrayList<>();


    int i = 0;
    while (csvReader.readRecord()) {

        String keyword = csvReader.get(0);
        String expect = csvReader.get(1);

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        String output = JSONArray.fromObject(artworksService.getArtworkDetails(Integer.valueOf(keyword))).toString();

        boolean isPassed = (output == expect) ? true : false;
        if (isPassed) {
            System.out.print(testCase+"-"+(++i)+" passed\n" );
        } else {
            System.out.print(testCase+"-"+(++i)+" failed\n" );
        }
        System.out.println("output:" + output);
        System.out.println("expect:" + expect);
        //将测试结果放在列表里
        passedList.add(isPassed);
        System.out.println("\n");
    }
    //断言是为了调用junit工具来记录是否通过测试
    for (boolean p : passedList) {
        assert p;
    }
} 

/** 
* 
* Method: getArtworkDetails(Artworks artworks) 
* 
*/ 
@Test
public void testGetArtworkDetailsArtworks() throws Exception { 
//TODO: Test goes here...

} 

/** 
* 
* Method: getArtworkLikes(int artworkId) 
* 
*/ 
@Test
public void testGetArtworkLikes() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: saveClick(Clicks clicks) 
* 
*/ 
@Test
public void testSaveClick() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: saveLike(Likes likes) 
* 
*/ 
@Test
public void testSaveLike() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: isLike(int userId, int artworkId) 
* 
*/ 
@Test
public void testIsLike() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findFollowArtworks(int clientId, Pageable pageable) 
* 
*/ 
@Test
public void testFindFollowArtworks() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findAll() 
* 
*/ 
@Test
public void testFindAll() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: saveArtwork(Artworks artworks) 
* 
*/ 
@Test
public void testSaveArtwork() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findAll(Pageable pageable) 
* 
*/ 
@Test
public void testFindAllPageable() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findAllTimeSort(Pageable pageable) 
* 
*/ 
@Test
public void testFindAllTimeSort() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findAllRandSort() 
* 
*/ 
@Test
public void testFindAllRandSort() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findAllCommentSort(Pageable pageable) 
* 
*/ 
@Test
public void testFindAllCommentSort() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findAllLikeSort(Pageable pageable) 
* 
*/ 
@Test
public void testFindAllLikeSort() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: saveComment(Comments comments) 
* 
*/ 
@Test
public void testSaveComment() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findBySearchKey(String key, Pageable pageable) 
* 
*/ 
@Test
public void testFindBySearchKey() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findAllRandSort(Pageable pageable) 
* 
*/ 
@Test
public void testFindAllRandSortPageable() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findBySearchAll(String key, Pageable pageable) 
* 
*/ 
@Test
public void testFindBySearchAll() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findByUserNameAndTag(String userName, String tagName, Pageable pageable) 
* 
*/ 
@Test
public void testFindByUserNameAndTag() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findByUserNameAndWorkName(String userName, String artworkName, Pageable pageable) 
* 
*/ 
@Test
public void testFindByUserNameAndWorkName() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findByWorkNameAndTag(String artworkName, String tagName, Pageable pageable) 
* 
*/ 
@Test
public void testFindByWorkNameAndTag() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findByAllKey(String userName, String artworkName, String tagName, Pageable pageable) 
* 
*/ 
@Test
public void testFindByAllKey() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findUserLikes(int userId, Pageable pageable) 
* 
*/ 
@Test
public void testFindUserLikes() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: countClicksPerMonth(int artworkId) 
* 
*/ 
@Test
public void testCountClicksPerMonth() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: countClicksBySex(int artworkId) 
* 
*/ 
@Test
public void testCountClicksBySex() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findByWorkName(String artworkName, Pageable pageable) 
* 
*/ 
@Test
public void testFindByWorkName() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findByArtistName(String userName, Pageable pageable) 
* 
*/ 
@Test
public void testFindByArtistName() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findByArtistNameEX(String userName, Pageable pageable) 
* 
*/ 
@Test
public void testFindByArtistNameEX() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findByTagName(String tagName, Pageable pageable) 
* 
*/ 
@Test
public void testFindByTagName() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findByTagNameEX(String tagName, Pageable pageable) 
* 
*/ 
@Test
public void testFindByTagNameEX() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findNameByworkId(int workId) 
* 
*/ 
@Test
public void testFindNameByworkId() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findTagListPopular(String start, String end) 
* 
*/ 
@Test
public void testFindTagListPopular() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findAllArtworkIdLikeSort(String start, String end) 
* 
*/ 
@Test
public void testFindAllArtworkIdLikeSort() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findArtworkWeekly(String start, String end) 
* 
*/ 
@Test
public void testFindArtworkWeekly() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findSimilarTag(String key) 
* 
*/ 
@Test
public void testFindSimilarTag() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: findSimilarTagEX(String key) 
* 
*/ 
@Test
public void testFindSimilarTagEX() throws Exception { 
//TODO: Test goes here... 
} 


} 
