package com.automationanywhere.botcommand.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.data.model.table.Row;

public class test {

	
	private static final Logger logger = LogManager.getLogger(test.class);
	
	static long now; 
	
	static Boolean hasTableHeader = false;
	
	
	public static void main(String[] args)  throws Exception {
		
		test();
		
	}
	
	public static  String  test() {
		// TODO Auto-generated method stub

  	String bodyHTML = "                                 <thead>                                        <tr><th data-sortable=\"\" style=\"width: 16.5975%;\"><a href=\"#\" class=\"dataTable-sorter\">Card Number</a></th><th data-sortable=\"\" style=\"width: 13.9696%;\"><a href=\"#\" class=\"dataTable-sorter\">Expiration</a></th><th data-sortable=\"\" style=\"width: 11.4799%;\"><a href=\"#\" class=\"dataTable-sorter\">Level</a></th><th data-sortable=\"\" style=\"width: 14.5228%;\"><a href=\"#\" class=\"dataTable-sorter\">Brand</a></th><th data-sortable=\"\" style=\"width: 8.85201%;\"><a href=\"#\" class=\"dataTable-sorter\">Card Type</a></th><th data-sortable=\"\" style=\"width: 13.0014%;\"><a href=\"#\" class=\"dataTable-sorter\">Last Name</a></th><th data-sortable=\"\" style=\"width: 16.8741%;\"><a href=\"#\" class=\"dataTable-sorter\">City/State</a></th><th data-sortable=\"\" style=\"width: 7.60719%;\"><a href=\"#\" class=\"dataTable-sorter\">Zip</a></th><th data-sortable=\"\" style=\"width: 11.8949%;\"><a href=\"#\" class=\"dataTable-sorter\">Country</a></th><th data-sortable=\"\" style=\"width: 9.12863%;\"><a href=\"#\" class=\"dataTable-sorter\">Price</a></th><th data-sortable=\"\" style=\"width: 8.85201%;\"><a href=\"#\" class=\"dataTable-sorter\">Buy?</a></th></tr>                                    </thead>                                    <tbody id=\"transactions\"><tr><td>669445**********</td><td>10/26</td><td>GOLD</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153636/mastercard.png\"> MASTERCARD</td><td>CREDIT</td><td>Millery</td><td>Baltimore;MD</td><td>21239</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>580578**********</td><td>7/30</td><td>CLASSIC</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/amex.png\"> AMERICAN EXPRESS</td><td>CREDIT</td><td>Cosford</td><td>Bakersfield;CA</td><td>93305</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>688886**********</td><td>1/30</td><td>PLATINUM</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/visa.png\"> VISA</td><td>DEBIT</td><td>Ivasechko</td><td>Gaithersburg;MD</td><td>20883</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>232444**********</td><td>11/27</td><td>PLATINUM</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/amex.png\"> AMERICAN EXPRESS</td><td>CREDIT</td><td>Lowde</td><td>Sterling;VA</td><td>20167</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>916932**********</td><td>10/28</td><td>CLASSIC</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153636/mastercard.png\"> MASTERCARD</td><td>CREDIT</td><td>Ivey</td><td>Chicago;IL</td><td>60646</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>720581**********</td><td>6/29</td><td>CLASSIC</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/visa.png\"> VISA</td><td>CREDIT</td><td>Pedroli</td><td>Corpus Christi;TX</td><td>78470</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>542044**********</td><td>1/29</td><td>GOLD</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153636/mastercard.png\"> MASTERCARD</td><td>DEBIT</td><td>Lingfoot</td><td>Peoria;IL</td><td>61635</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>862718**********</td><td>12/30</td><td>CLASSIC</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153636/mastercard.png\"> MASTERCARD</td><td>CREDIT</td><td>Clemonts</td><td>Boston;MA</td><td>02124</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>889159**********</td><td>7/28</td><td>CLASSIC</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/amex.png\"> AMERICAN EXPRESS</td><td>DEBIT</td><td>Eyre</td><td>Santa Monica;CA</td><td>90410</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>969311**********</td><td>3/25</td><td>CLASSIC</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/visa.png\"> VISA</td><td>CREDIT</td><td>Hegg</td><td>Orange;CA</td><td>92668</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>903000**********</td><td>3/29</td><td>PLATINUM</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/amex.png\"> AMERICAN EXPRESS</td><td>CREDIT</td><td>Peak</td><td>Savannah;GA</td><td>31410</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>915127**********</td><td>6/30</td><td>CLASSIC</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153636/mastercard.png\"> MASTERCARD</td><td>DEBIT</td><td>Lahive</td><td>Cincinnati;OH</td><td>45249</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>938173**********</td><td>8/27</td><td>PLATINUM</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153636/mastercard.png\"> MASTERCARD</td><td>CREDIT</td><td>Goldsack</td><td>Roanoke;VA</td><td>24048</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>315292**********</td><td>1/26</td><td>PLATINUM</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/amex.png\"> AMERICAN EXPRESS</td><td>CREDIT</td><td>Rubertelli</td><td>Amarillo;TX</td><td>79188</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>560974**********</td><td>7/30</td><td>CLASSIC</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/visa.png\"> VISA</td><td>DEBIT</td><td>Stempe</td><td>Rochester;NY</td><td>14639</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>335372**********</td><td>5/26</td><td>CLASSIC</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153636/mastercard.png\"> MASTERCARD</td><td>CREDIT</td><td>Ord</td><td>Huntington Beach;CA</td><td>92648</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>634297**********</td><td>6/28</td><td>GOLD</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153636/mastercard.png\"> MASTERCARD</td><td>CREDIT</td><td>Found</td><td>Pasadena;CA</td><td>91186</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>163742**********</td><td>7/28</td><td>PLATINUM</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/visa.png\"> VISA</td><td>DEBIT</td><td>Edmed</td><td>Macon;GA</td><td>31217</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>537232**********</td><td>5/29</td><td>PLATINUM</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153636/mastercard.png\"> MASTERCARD</td><td>CREDIT</td><td>Lorinez</td><td>Honolulu;HI</td><td>96840</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>580578**********</td><td>7/30</td><td>CLASSIC</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/amex.png\"> AMERICAN EXPRESS</td><td>CREDIT</td><td>Cosford</td><td>Bakersfield;CA</td><td>93305</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>726168**********</td><td>8/25</td><td>CLASSIC</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/amex.png\"> AMERICAN EXPRESS</td><td>DEBIT</td><td>Stowell</td><td>Oklahoma City;OK</td><td>73135</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>123389**********</td><td>4/28</td><td>GOLD</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153636/mastercard.png\"> MASTERCARD</td><td>DEBIT</td><td>Oxley</td><td>Marietta;GA</td><td>30061</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>613171**********</td><td>12/26</td><td>GOLD</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/visa.png\"> VISA</td><td>DEBIT</td><td>Meharry</td><td>Saint Louis;MO</td><td>63167</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>626244**********</td><td>4/29</td><td>GOLD</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/amex.png\"> AMERICAN EXPRESS</td><td>CREDIT</td><td>McMullen</td><td>Pittsburgh;PA</td><td>15215</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>870358**********</td><td>4/25</td><td>GOLD</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/amex.png\"> AMERICAN EXPRESS</td><td>DEBIT</td><td>Aymeric</td><td>Pittsburgh;PA</td><td>15274</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>483933**********</td><td>8/30</td><td>GOLD</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153636/mastercard.png\"> MASTERCARD</td><td>CREDIT</td><td>Sawney</td><td>Oklahoma City;OK</td><td>73109</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>308537**********</td><td>3/25</td><td>GOLD</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153636/mastercard.png\"> MASTERCARD</td><td>CREDIT</td><td>Happel</td><td>Buffalo;NY</td><td>14210</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>338804**********</td><td>11/27</td><td>PLATINUM</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/visa.png\"> VISA</td><td>CREDIT</td><td>Farnes</td><td>Canton;OH</td><td>44760</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>286081**********</td><td>12/28</td><td>GOLD</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/visa.png\"> VISA</td><td>CREDIT</td><td>Farquharson</td><td>Fort Worth;TX</td><td>76162</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>323903**********</td><td>9/27</td><td>CLASSIC</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153636/mastercard.png\"> MASTERCARD</td><td>CREDIT</td><td>Jentet</td><td>Omaha;NE</td><td>68179</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>181484**********</td><td>2/26</td><td>PLATINUM</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/amex.png\"> AMERICAN EXPRESS</td><td>DEBIT</td><td>Clamp</td><td>Round Rock;TX</td><td>78682</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>210541**********</td><td>7/30</td><td>CLASSIC</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153636/mastercard.png\"> MASTERCARD</td><td>DEBIT</td><td>Papierz</td><td>Grand Rapids;MI</td><td>49505</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>520609**********</td><td>3/27</td><td>PLATINUM</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153636/mastercard.png\"> MASTERCARD</td><td>DEBIT</td><td>Dewicke</td><td>Bronx;NY</td><td>10454</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>896187**********</td><td>8/28</td><td>PLATINUM</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/visa.png\"> VISA</td><td>CREDIT</td><td>Jeppe</td><td>Juneau;AK</td><td>99812</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>908662**********</td><td>3/27</td><td>GOLD</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/amex.png\"> AMERICAN EXPRESS</td><td>CREDIT</td><td>Giovanardi</td><td>Mc Keesport;PA</td><td>15134</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>710835**********</td><td>5/27</td><td>PLATINUM</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/amex.png\"> AMERICAN EXPRESS</td><td>DEBIT</td><td>Blakiston</td><td>Harrisburg;PA</td><td>17126</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>990352**********</td><td>7/28</td><td>PLATINUM</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153636/mastercard.png\"> MASTERCARD</td><td>DEBIT</td><td>Jobbing</td><td>Springfield;IL</td><td>62718</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>916414**********</td><td>8/27</td><td>GOLD</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/visa.png\"> VISA</td><td>CREDIT</td><td>Dace</td><td>Anaheim;CA</td><td>92805</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>635219**********</td><td>2/26</td><td>PLATINUM</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/visa.png\"> VISA</td><td>DEBIT</td><td>Leyshon</td><td>Port Washington;NY</td><td>11054</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>718812**********</td><td>5/28</td><td>CLASSIC</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153636/mastercard.png\"> MASTERCARD</td><td>CREDIT</td><td>Grand</td><td>Baltimore;MD</td><td>21290</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>441477**********</td><td>9/28</td><td>GOLD</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/amex.png\"> AMERICAN EXPRESS</td><td>CREDIT</td><td>Doctor</td><td>Lincoln;NE</td><td>68517</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>118001**********</td><td>1/28</td><td>PLATINUM</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/visa.png\"> VISA</td><td>DEBIT</td><td>Webbe</td><td>Oklahoma City;OK</td><td>73104</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>671087**********</td><td>5/27</td><td>PLATINUM</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/amex.png\"> AMERICAN EXPRESS</td><td>CREDIT</td><td>Skechley</td><td>Northridge;CA</td><td>91328</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>379591**********</td><td>3/26</td><td>CLASSIC</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/visa.png\"> VISA</td><td>CREDIT</td><td>Doppler</td><td>Dayton;OH</td><td>45470</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>257994**********</td><td>4/26</td><td>GOLD</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/amex.png\"> AMERICAN EXPRESS</td><td>DEBIT</td><td>Hilbourne</td><td>Atlanta;GA</td><td>30375</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>237204**********</td><td>4/30</td><td>PLATINUM</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/visa.png\"> VISA</td><td>CREDIT</td><td>Collibear</td><td>Evansville;IN</td><td>47705</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>916414**********</td><td>8/27</td><td>GOLD</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/visa.png\"> VISA</td><td>CREDIT</td><td>Dace</td><td>Anaheim;CA</td><td>92805</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>472923**********</td><td>8/28</td><td>GOLD</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153636/mastercard.png\"> MASTERCARD</td><td>CREDIT</td><td>Rushman</td><td>Atlanta;GA</td><td>31196</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>561069**********</td><td>3/28</td><td>PLATINUM</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153636/mastercard.png\"> MASTERCARD</td><td>CREDIT</td><td>Brame</td><td>San Diego;CA</td><td>92132</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr><tr><td>776633**********</td><td>9/30</td><td>PLATINUM</td><td><img src=\"https://s3-us-west-2.amazonaws.com/aai-devportal-media/wp-content/uploads/2022/02/14153637/visa.png\"> VISA</td><td>CREDIT</td><td>Ginity</td><td>Birmingham;AL</td><td>35279</td><td>US</td><td>$19.99</td><td><button type=\"button\" class=\"btn btn-primary btn-sm\" onclick=\"showModal()\">Buy</button></td></tr></tbody>                               ";
  	
    Document bodyObj = Jsoup.parse(bodyHTML);
    
    
    
    Element bodyElement = bodyObj.body();
    
   // System.out.println(bodyElement.toString());
    
    List<Row> rows = new ArrayList<Row>();	 
    
    now = System.currentTimeMillis();
   
    List<Element> header = bodyElement.select("thead");
    System.out.println(header.size());
    logger.info("Find Thead "+(System.currentTimeMillis()-now));
    if (header.size() > 0) {
    	if (header.get(0).select("tr").size() > 0) {
	    	Element headerRow = header.get(0).select("tr").get(0);
	    	List<Value> headers = getHeaders(headerRow);
	    	rows.add(new Row(headers));
    	}
    	
    }
    logger.info("Find Tbody "+(System.currentTimeMillis()-now));
    List<Element> body = bodyObj.select("tbody");
    System.out.println(body.size());
    if (body.size() > 0) {
    	bodyElement = body.get(0);
	    
    }
    logger.info("Before Get Rows "+(System.currentTimeMillis()-now));
    rows = getRows(bodyElement,rows);
    
    
    System.out.println(hasTableHeader);
    
    for (Iterator iterator = rows.iterator(); iterator.hasNext();) {
		Row row = (Row) iterator.next();
		List<Value> values = row.getValues();
		for (Iterator iterator2 = values.iterator(); iterator2.hasNext();) {
			Value value = (Value) iterator2.next();
			System.out.print(value.toString()+",");
		}		
		System.out.println("");
	}
    logger.info("After Get Rows "+(System.currentTimeMillis()-now));
	return bodyHTML;
    
 //   driver.manage().timeouts().implicitlyWait(currentDuration);


}

	private static List<Value> getHeaders(Element headerrow) {
		
		List<Value> headerValues = new ArrayList<Value>();
	List<Element> cellRow = headerrow.select("th");
	if (cellRow.size()> 0) {
		for (Iterator iterator2 = cellRow.iterator(); iterator2.hasNext();) {
			Element cell = (Element) iterator2.next();
			headerValues.add(new StringValue(cell.text()));
		}
		hasTableHeader = true;
	 }
	 return headerValues;
	}
	
	
	static ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
	    int count = 1;

	    @Override
	    public Thread newThread(Runnable runnable) {
	        return new Thread(runnable, "custom-executor-" + count++);
	    }
	});
	
	private static List<Row>  getRows(Element body,List<Row> rows ) {
		
    logger.info("getRows before Rows  "+(System.currentTimeMillis()-now));
		List<Element> allRows = body.select("tr");
    logger.info("getRows after Rows "+(System.currentTimeMillis()-now));
	List<CompletableFuture<Row>> cfs = new ArrayList<>();
    logger.info("Row Iterator before "+(System.currentTimeMillis()-now));
    
  	ExecutorService executor = Executors.newFixedThreadPool(1, new ThreadFactory() {
  	    int count = 1;

  	    @Override
  	    public Thread newThread(Runnable runnable) {
  	        return new Thread(runnable, "custom-executor-" + count++);
  	    }
  	});
  	
  	

	    for (Iterator iterator = allRows.iterator(); iterator.hasNext();) {
			 Element row = (Element) iterator.next();
			 cfs.add(CompletableFuture.completedFuture(row).thenApplyAsync(s -> {
				String threadname = Thread.currentThread().getName();
				  logger.info("Row before "+threadname+ " "+(System.currentTimeMillis()-now));
				 List<Value> rowValues = new ArrayList<Value>();
				 if (row.select("th").size() > 0 && !hasTableHeader) {
					 rowValues = getHeaders(row);
				 }
				 else {
					 List<Element> cellRow = row.select("td");
					 for (Iterator iterator2 = cellRow.iterator(); iterator2.hasNext();) {
						 Element cell = (Element) iterator2.next();
						 rowValues.add(new StringValue(cell.text()));
					 }
				 }
				 logger.info("Row after "+threadname+ " "+(System.currentTimeMillis()-now));
				 return new Row(rowValues);
			},executor));
		}
    logger.info("Row Iterator after "+(System.currentTimeMillis()-now));
	    Boolean allDone;
	    
    logger.info("DoneIterator before "+(System.currentTimeMillis()-now));
	    do {
	 	   allDone = true;
	 	    for (Iterator iterator = cfs.iterator(); iterator.hasNext();) {
				CompletableFuture<Row> completableFuture = (CompletableFuture<Row>) iterator.next();
				if (!completableFuture.isDone()) {
					allDone = false;
				}
			  
	 	   }
		
	} while (!allDone);
	    
	    
    logger.info("DoneIterator after "+(System.currentTimeMillis()-now));
    
    logger.info("AddIterator before "+(System.currentTimeMillis()-now));
    
	    for (Iterator iterator = cfs.iterator(); iterator.hasNext();) {
		CompletableFuture<Row> completableFuture = (CompletableFuture<Row>) iterator.next();
			    rows.add(completableFuture.getNow(null));	
		}
	    
    logger.info("AddIterator after "+(System.currentTimeMillis()-now));
	    
	return rows;
	}

	}
	
