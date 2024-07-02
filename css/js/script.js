$(document).ready(function(){
	const date = new Date();
	
	let currentMonth = date.getMonth()+1;
	
	if(currentMonth<10){
		currentMonth="0"+currentMonth;
	}
	
	$("#searchdate").attr("min", `${date.getFullYear()}-${currentMonth}-${date.getDate()}`);

	$("#login-form-btn").click(function(){
		$("#login-transparent-screen").show();
	});

	$("#register-form-btn").click(function(){
		$("#register-transparent-screen").show();
	});

	$("#open_login_form").click(function(){
		$("#login-transparent-screen").show();
		$("#register-transparent-screen").hide();
		$("#reg_form").trigger("reset");
	});

	$("#open-register").click(function(){
		$("#register-transparent-screen").show();
		$("#login-transparent-screen").hide();
		$("#login_form").trigger("reset");
	});

	$("#login-close-img").click(function(){
		$("#login-transparent-screen").hide();
		$("#login_form").trigger("reset");
	});

	$("#register-close-img").click(function(){
		$("#register-transparent-screen").hide();
		$("#reg_form").trigger("reset");
	});

	$("#login-transparent-screen").click(function(event){
		let loginScreen = document.getElementById("login-transparent-screen");
		if(event.target==loginScreen){
			$("#login-transparent-screen").hide();
			$("#login_form").trigger("reset");
		}
	});
	
	$("#bus-close-img").click(function(){
		$("#busesphotos-transaparent-screen").hide();
		location.reload();
	});

	$("#register-transparent-screen").click(function(event){
		let registerScreen = document.getElementById("register-transparent-screen");
		if(event.target==registerScreen){
			$("#register-transparent-screen").hide();
			$("#reg_form").trigger("reset");
		}
	});
	
	$("#boarding-close-img").click(function(){
		$("#boarding-point-transparent-screen").hide();
	});
	
	$("#destination-close-img").click(function(){
		$("#destination-point-transparent-screen").hide();
	});
	
	$("#bookseat-close-img").click(function(){
		$("#bookseat-transparent-screen").hide();
		$("#bookseat-form").trigger("reset");
	});

	$("#firstname").on({
		keypress:function(event){
			validate.isValidFirstName(this);
		},
		keyup:function(){
			validate.isValidFirstName(this);
		} 
	});

	$("#lastname").on({
		keypress:function(){
			validate.isValidLastName(this);
		},
		keyup:function(){
			validate.isValidLastName(this);
		} 
	});

	$("#mobile_number").on({
		keypress:function(){
			validate.isValidMobileNumber(this);
		},
		keyup:function(){
			validate.isValidMobileNumber(this);
		} 
	});

	$("#email").on({
		keypress:function(){
			validate.isValidEmail(this);
		},
		keyup:function(){
			validate.isValidEmail(this);
		} 
	});

	$("#password").on({
		keypress:function(){
			validate.isValidPassword(this);
		},
		keyup:function(){
			validate.isValidPassword(this);
		} 
	});

	$("#cpassword").on({
		keypress:function(){
			validate.isValidConfirmPassword(this);
		},
		keyup:function(){
			validate.isValidConfirmPassword(this);
		} 
	});

	$("#firstname,#lastname,#mobile_number,#email,#password,#cpassword,#login_email,#login_password").on("paste",function(){
		validate.restrictPaste(event);
	});

	$("#firstname,#lastname,#mobile_number,#email,#password,#cpassword,#login_email,#login_password").on("copy",function(){
		validate.restrictCopy(event);
	});
	
	$("#busname").on({
		keypress:function(event){
			validate.restrictNumber(event);
		}
	});
	
	$("#busstartpoint").on({
		keypress:function(event){
			validate.restrictNumber(event);
		}
	})
	
	$("#busendpoint").on({
		keypress:function(event){
			validate.restrictNumber(event);
		}
	})
	
	$("#busroute").on({
		keypress:function(event){
			validate.restrictNumber(event);
		}
	})
	
	$("#busticketprice").on({
		keypress:function(event){
			validate.restrictCharacters(event);
		}
	})
	
	$("#bustotalseats").on({
		keypress:function(event){
			validate.restrictCharacters(event);
		}
	})

	$("#reg_form").submit(function(){
		let firstname = $("#firstname");
		let lastname = $("#lastname");
		let mobile_number = $("#mobile_number");
		let email = $("#email");
		let password = $("#password");
		let cpassword = $("#cpassword");
		
		if(!validate.checkSubmit(firstname.val(),lastname.val(),mobile_number.val(),email.val(),password.val(),cpassword.val())){
			$(".alert_data").html("Failed To Register!");
			$(".check_img").attr("src","./images/cross_image.png");
			$(".alert_popup").attr("style","background-image:linear-gradient(to top right,#F6AAAA,white,#F6AAAA)");

			$("#error-transparent-screen").show();
			
			setTimeout(()=>{
				$("#error-transparent-screen").hide();
			},2000);
		}
	});
	
	$("#bookseat-form").submit(function(event){
		let seats = $("#sendseats");
		
		if(seats.val()==""){
			event.preventDefault();
			alert("Please Select Seats To Book!")
		}
	});
	
	setTimeout(function() {
		 $("#transparent-screen-error").fadeOut();
    }, 3000);
	
	$("#login_form").submit(function(event){
		event.preventDefault();
	});
	
	$("#add-bus-form").submit(function(){
		let busname = $("#busname");
		let startpoint = $("#busstartpoint");
		let endpoint = $("#busendpoint");
		let route = $("#busroute");
		let busregistrationnumber = $("#busregistrationnumber");
		let ticketprice = $("#busticketprice");
		let totalseats = $("#bustotalseats");
		let starttime = $("#busstarttime");
		let endtime = $("#busendtime");
		
		if(!validate.checkAddBus(busname.val(),startpoint.val(),endpoint.val(),route.val(),busregistrationnumber.val(),ticketprice.val(),totalseats.val(),starttime.val(),endtime.val())){
			$(".alert_data").html("Failed To Add Bus!");
			$(".check_img").attr("src","./images/cross_image.png");
			$(".alert_popup").attr("style","background-image:linear-gradient(to top right,#F6AAAA,white,#F6AAAA)");

			$("#error-transparent-screen").show();
			
			setTimeout(()=>{
				$("#error-transparent-screen").hide();
			},2000);
		}
	})
	
	setTimeout(()=>{
		$("#bus-status-message").hide();
	},5000);
	
	setTimeout(()=>{
		$("#user-status-message").hide();
	},5000);
	
});

//created a object of all validations
let validate = new Validate();

function Validate(){

	this.isValidFirstName = isValidFirstName;
	this.isValidLastName =  isValidLastName;
	this.isValidMobileNumber = isValidMobileNumber;
	this.isValidEmail = isValidEmail;
	this.isValidPassword = isValidPassword;
	this.isValidConfirmPassword = isValidConfirmPassword;
	this.checkSubmit = checkSubmit;
	this.restrictCopy = restrictCopy;
	this.restrictPaste = restrictPaste;
	this.checkAddBus = checkAddBus;
	this.restrictNumber = restrictNumber;
	this.restrictCharacters = restrictCharacters;

	//conditions for firstname
	function isValidFirstName(e){

		if((event.keyCode>=32 && event.keyCode<=64) || (event.keyCode>=91 && event.keyCode<=96) || (event.keyCode>=123 && event.keyCode<=126)){
			event.preventDefault();
		}

		let firstname_pattern = /^(^[a-zA-Z]{2,15})$/g;

		if(e.value==""){
			$("#firstname_error").html("Firstname Can't Be Empty!");
		}
		else if(!firstname_pattern.test(e.value)){
			$("#firstname_error").html("Min:2 and Max:15 alphabets are mandatory");
		}
		else{
			$("#firstname_error").html("");
		}
	}
	
	//conditions for last name
	function isValidLastName(e){

		if((event.keyCode>=32 && event.keyCode<=64) || (event.keyCode>=91 && event.keyCode<=96) || (event.keyCode>=123 && event.keyCode<=126) || !allow_input){
			event.preventDefault();
		}

		let lastname_pattern = /^(^[a-zA-Z]{2,15})$/g;
		
		if(e.value==""){
			$("#lastname_error").html("Lastname Can't Be Empty!");
		}
		else if(!lastname_pattern.test(e.value)){
			$("#lastname_error").html("Min:2 and Max:15 alphabets are mandatory");
		}
		else{
			$("#lastname_error").html("");
		}
	}

	//conditions for mobile number
	function isValidMobileNumber(e){
		
		if(!(event.keyCode>=48 && event.keyCode<=57)){
			event.preventDefault();
		}

		let mobile_no_pattern = /^(^[6-9][0-9]{1,9})$/g;

		if(e.value==""){
			$("#mobile_number_error").html("Mobile Number Can't Be Empty");
		}
		else if(!mobile_no_pattern.test(e.value)){
			$("#mobile_number_error").html("Enter 10 Digit Mobile Number Only from Starting digits with 6-9");
		}
		else{
			$("#mobile_number_error").html("");
		}
	}

	//conditions for email
	function isValidEmail(e){

		let email_pattern = /^(^[a-z0-9][a-z0-9]*((\.[a-z0-9]+)|(\_[a-z0-9]+))*[@][a-z]+[.][a-z]{2,4}([.][a-z]{2})?)$/g;

		if(e.value==""){
			$("#email_error").html("Email Can't be Empty");
		}
		else if(!email_pattern.test(e.value)){
			$("#email_error").html("Please Enter a Valid Email Without Any Special Character Except @ and .");
		}
		else{
			$("#email_error").html("");
		}
	}

	//conditions for password
	function isValidPassword(e){

		let password_pattern = /^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,15}$/g;

		if(e.value==""){
			$("#password_error").html("Password Can't be Empty");
		}
		else if(!password_pattern.test(e.value)){
			$("#password_error").html("Password Length Must Be Between 8 to 15 with atleast 1 capital letter, 1 number and 1 Special Characters Between !@#$%^&*");
		}
		else{
			$("#password_error").html("");
		}
	}
	
	//conditions for confirm password
	function isValidConfirmPassword(e){

		let user_password = document.getElementById("password").value;
		let cpassword = document.getElementById("cpassword").value;

		if(e.value==""){
			$("#cpassword_error").html("Confirm Password Can't be Empty");
		}
		else if(user_password!=cpassword){
			$("#cpassword_error").html("Password and Confirm Password Does Not Match");
			cpassword_error.style.color="white";
		}
		else{
			$("#cpassword_error").html("Password and Confirm Password Matched!");
			$("#cpassword_error").css("color","green");
		}
	}

	//restrict copy
	function restrictCopy(e){
		e.preventDefault();
	}

	//restrict paste
	function restrictPaste(e){
		e.preventDefault();
	}
	
	function restrictNumber(event){
		if((event.keyCode>=33 && event.keyCode<=64) || (event.keyCode>=91 && event.keyCode<=96) || (event.keyCode>=123 && event.keyCode<=126) || !allow_input){
			event.preventDefault();
		}
	}
	
	function restrictCharacters(event){
		if(!(event.keyCode>=48 && event.keyCode<=57)){
			event.preventDefault();
		}
	}

	//final check on submit
	function checkSubmit(firstname,lastname,mobile_number,email,password,cpassword){
	
		let firstname_pattern = /^(^[a-zA-Z]{2,15})$/g;
		let lastname_pattern = /^(^[a-zA-Z]{2,15})$/g;
		let mobile_no_pattern = /^(^[6-9][0-9]{1,9})$/g;
		let email_pattern = /^(^[a-z0-9][a-z0-9]*((\.[a-z0-9]+)|(\_[a-z0-9]+))*[@][a-z]+[.][a-z]{2,4}([.][a-z]{2})?)$/g;
		let password_pattern = /^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,15}$/g;
	
		if(firstname==""){
			event.preventDefault();
			$("#firstname_error").html("First Name Can't Be Empty");
			return false;
		}
		else if(!firstname_pattern.test(firstname)){
			event.preventDefault();
			$("#firstname_error").html("Min:2 and Max:15 alphabets are mandatory");
			return false;
		}
		else{
			$("#firstname_error").html("");
		}

		if(lastname==""){
			event.preventDefault();
			$("#lastname_error").html("Last Name Can't Be Empty");
			return false;
		}
		else if(!lastname_pattern.test(lastname)){
			event.preventDefault();
			$("#lastname_error").html("Min:2 and Max:15 alphabets are mandatory");
			return false;
		}
		else{
			$("#lastname_error").html("");
		}

		if(mobile_number==""){
			event.preventDefault();
			$("#mobile_number_error").html("Mobile Number Can't Be Empty");
			return false;
		}
		else if(!mobile_no_pattern.test(mobile_number)){
			event.preventDefault();
			$("#mobile_number_error").html("Enter 10 Digit Mobile Number Only from Starting digits with 6-9");
			return false;
		}
		else{
			$("#mobile_number_error").html("");
		}

		if(email==""){
			event.preventDefault();
			$("#email_error").html("Email Can't Be Empty");
			return false;
		}
		else if(!email_pattern.test(email)){
			event.preventDefault();
			$("#email_error").html("Please Enter a Valid Email!");
			return false;
		}
		else{
			$("#email_error").html("");
		}

		if(password==""){
			event.preventDefault();
			$("#password_error").html("Password Can't Be Empty");
			return false;
		}
		else if(!password_pattern.test(password)){
			event.preventDefault();
			$("#password_error").html("Password Length Must Be Between 8 to 15 with atleast 1 capital letter, 1 number and 1 Special Characters Between !@#$%^&*");
			return false;
		}
		else{
			$("#password_error").html("");
		}

		if(cpassword==""){
			event.preventDefault();
			$("#cpassword_error").html("Confirm Password Can't Be Empty");
			return false;
		}
		else if(password!=cpassword){
			event.preventDefault();
			$("#cpassword_error").html("Password and Confirm Password Did Not Match");
			return false;
		}
		else{
			$("#cpassword_error").html("");
		}

		return true;
	}
	
	function checkAddBus(busname,startpoint,endpoint,route,busregistrationnumber,ticketprice,totalseats,starttime,endtime){
		if(busname==""){
			event.preventDefault();
			return false;
		}

		if(startpoint==""){
			event.preventDefault();
			return false;
		}
		
		if(endpoint==""){
			event.preventDefault();
			return false;
		}
		
		if(route==""){
			event.preventDefault();
			return false;
		}
		
		if(busregistrationnumber==""){
			event.preventDefault();
			return false;
		}
		
		if(ticketprice==""){
			event.preventDefault();
			return false;
		}
		
		if(totalseats="" || totalseats%6!=0){
			event.preventDefault();
			$("#seats-error").html("Seats Must Be Multiple of 6 Only");
			return false;
		}
		
		if(starttime==""){
			event.preventDefault();
			return false;
		}
		
		if(endtime==""){
			event.preventDefault();
			return false;
		}
		
		return true;
	}
}

let userFeatures = new userActions();

function userActions(){
	this.showSeats = showSeats;
	this.hideSeats = hideSeats;
	this.showLoginAlert = showLoginAlert;
	this.showImages = showImages;
	this.showBoardingPointForm = showBoardingPointForm;
	this.showDestinationPointForm = showDestinationPointForm;
	this.loadPoints = loadPoints;
	this.cancelTicket = cancelTicket;
	this.changeBusStatus = changeBusStatus;
	this.changeUserStatus = changeUserStatus;
	this.changeBookingStatus = changeBookingStatus;
	this.selectSeat = selectSeat;
	this.showGenderError = showGenderError;
	
	function showSeats(e){
		let id = e.id;
		let seats = e.dataset.seats;
		let currentUserGender = e.dataset.currentgender;
		let searchedDate = e.dataset.date;
		
		let seatArea = "#seats-"+id;
		
		$(seatArea).show();
		
		let viewSeats = document.querySelectorAll(".view-seat");
		
		viewSeats.forEach(function(button) {
			button.removeAttribute("onclick");
		});
		
		let dividedSeatArea = seats/2;
		
		let columnsCount = dividedSeatArea/3;
			
		let lowerSeats = document.getElementById(`lower-seats-${id}`);
		lowerSeats.setAttribute("style","border-right:2px solid #d84e55");
		
		lowerSeats.innerHTML="";
		
		let lowerHeading = document.createElement("h2");
		lowerSeats.appendChild(lowerHeading);
		lowerHeading.setAttribute("class","text-center m-5");
		lowerHeading.innerHTML="Lower Deck";
		
		let idCount = 1;
		
		for(row=0;row<3;row++){
			let addrow = document.createElement("div");
			lowerSeats.appendChild(addrow);
			
			for(column=0;column<columnsCount;column++){
				let addcolumn = document.createElement("div");
				addrow.appendChild(addcolumn);
				addcolumn.setAttribute("class","block f-left");
				
				let seatDiv = document.createElement("div");
				addcolumn.appendChild(seatDiv);
				seatDiv.setAttribute("class","seat m-5");
				seatDiv.setAttribute("id",`L${idCount}`);
				seatDiv.setAttribute("onclick","userFeatures.selectSeat(this)");
				
				let seatNumber = document.createElement("h3");
				seatDiv.appendChild(seatNumber);
				seatNumber.setAttribute("class","seat-no");
				seatNumber.innerHTML = `L${idCount}`;
				
				idCount++;
			}
			
			let cleardiv = document.createElement("div");
			addrow.appendChild(cleardiv);
			cleardiv.setAttribute("style","clear:both");
		}
		
		let upperSeats = document.getElementById(`upper-seats-${id}`);
		
		upperSeats.innerHTML="";
		
		let upperHeading = document.createElement("h2");
		upperSeats.appendChild(upperHeading);
		upperHeading.setAttribute("class","text-center m-5");
		upperHeading.innerHTML="Upper Deck";
		
		let newidCount = 1;
		
		for(row=0;row<3;row++){
			let addrow = document.createElement("div");
			upperSeats.appendChild(addrow);
			
			for(column=0;column<columnsCount;column++){
				let addcolumn = document.createElement("div");
				addrow.appendChild(addcolumn);
				addcolumn.setAttribute("class","block f-left");
				
				let seatDiv = document.createElement("div");
				addcolumn.appendChild(seatDiv);
				seatDiv.setAttribute("class","seat m-5");
				seatDiv.setAttribute("id",`U${newidCount}`);
				seatDiv.setAttribute("onclick","userFeatures.selectSeat(this)");
				
				let seatNumber = document.createElement("h3");
				seatDiv.appendChild(seatNumber);
				seatNumber.setAttribute("class","seat-no");
				seatNumber.innerHTML = `U${newidCount}`;
				
				newidCount++;
			}
			
			let cleardiv = document.createElement("div");
			addrow.appendChild(cleardiv);
			cleardiv.setAttribute("style","clear:both");
		}
		
		$.ajax({
        	method: "POST",
        	url: "getbookingsdata.jsp",
		   	data: {
				bus_id:id,
				searched_date:searchedDate
		   	},
        	success: function (res) {
        		for(i=0;i<res.bookings.length;i++){
        			let gender = res.bookings[i].gender;
            		let seats = res.bookings[i].seats;
            		
            		const seatArray = seats.split(",");
            		
            		for(j=0;j<seatArray.length;j++){
            			let seat = document.getElementById(seatArray[j]);
            			
            			if(gender=="female"){
            				seat.setAttribute("style","background-color:#FD73F7");
            				
            				if(currentUserGender!=gender){
            					
            					for(let k=1;k<=columnsCount;k++){
            						if(seatArray[j]==`L${k}`){
                						let parts = seatArray[j].match(/[a-zA-Z]+|[0-9]+/g);
                    					let currentSeat = parts[1];
                    					let lockSeat = columnsCount+parseInt(currentSeat);
                    					let lockBehind = document.getElementById(`L${lockSeat}`);
                    					lockBehind.setAttribute("onclick","userFeatures.showGenderError()");
                    				}
            					}
            					
            					for(let k=columnsCount+1;k<=columnsCount*2;k++){
            						if(seatArray[j]==`L${k}`){
                    					let parts = seatArray[j].match(/[a-zA-Z]+|[0-9]+/g);
                    					let currentSeat = parts[1];
                    					let lockSeat = parseInt(currentSeat)-columnsCount;
                    					let lockBehind = document.getElementById(`L${lockSeat}`);
                    					lockBehind.setAttribute("onclick","userFeatures.showGenderError()");
                    				}
            					}
                				
            					for(let k=1;k<=columnsCount;k++){
            						if(seatArray[j]==`U${k}`){
                    					let parts = seatArray[j].match(/[a-zA-Z]+|[0-9]+/g);
                    					let currentSeat = parts[1];
                    					let lockSeat = columnsCount+parseInt(currentSeat);
                    					let lockBehind = document.getElementById(`U${lockSeat}`);
                    					lockBehind.setAttribute("onclick","userFeatures.showGenderError()");
                    				}
            					}
                				
            					for(let k=columnsCount+1;k<=columnsCount*2;k++){
            						if(seatArray[j]==`U${k}`){
                    					let parts = seatArray[j].match(/[a-zA-Z]+|[0-9]+/g);
                    					let currentSeat = parts[1];
                    					let lockSeat = parseInt(currentSeat)-columnsCount;
                    					let lockBehind = document.getElementById(`U${lockSeat}`);
                    					lockBehind.setAttribute("onclick","userFeatures.showGenderError()");
                    				}
            					}
            				}
            				
            				seat.setAttribute("class","lockseat m-5");
                			seat.removeAttribute("onclick");
            			}
            			else{
            				seat.setAttribute("style","background-color:#4C7CFC");
            				seat.setAttribute("class","lockseat m-5");
                			seat.removeAttribute("onclick");
            			}
            		}
        		}
        		
        		let allBookedSeats = document.querySelectorAll(".lockseat");
        		
        		allBookedSeats.forEach(seat => {
        		    seat.removeAttribute('onclick');
        		});
        	}
 		});
	}
	
	function hideSeats(e){
		let id = e.id;
		let seats = e.dataset.seats;
		
		let dividedArea = seats/2;
		
		let seatArea = "#seats-"+id;
		
		$(seatArea).hide();
		
		let viewSeats = document.querySelectorAll(".view-seat");
		
		viewSeats.forEach(function(button) {
			button.setAttribute("onclick","userFeatures.showSeats(this)");
		});
		
		for(i=1;i<=dividedArea;i++){
			let lowerSeats = document.getElementById(`L${i}`);
			lowerSeats.remove();
		}
		
		for(j=1;j<=dividedArea;j++){
			let upperSeats = document.getElementById(`U${j}`);
			upperSeats.remove();
		}
	}
	
	function showLoginAlert(){
		alert("Please Login!");
	}
	
	function showImages(e){
		let busId = e.id;
		
		$.ajax({
        	method: "POST",
        	url: "getbusimages.jsp",
		   	data: {
				bus_id:busId
		   	},
        	success: function (res) {
        		if(res.status==200){
     				let i=0;
     				
     				setInterval(()=>{
     					if(i==res.busimages.length){
    	 					i=0;
    	 				}
    	 				let busimage = document.getElementById("bus-photo");
    	 				busimage.setAttribute("src",`${res.busimages[i].imagePath}`);
    	 				i++;
     				},2000);
     				$("#busesphotos-transaparent-screen").show();
        		}
        		else{
        			alert("Failed To Load Data!");
        		}
        	}
 		});
	}
	
	function showBoardingPointForm(e){
		let busId = e.id;
		
		let busInput = document.getElementById("busid");
		busInput.setAttribute("value",busId);
		
		$("#boarding-point-transparent-screen").show();
	}
	
	function showDestinationPointForm(e){
		let busId = e.id;
		
		let busInput = document.getElementById("busid1");
		busInput.setAttribute("value",busId);
		
		$("#destination-point-transparent-screen").show();
	}
	
	function loadPoints(e){
		$("#bookseat-transparent-screen").show();
		
		let busId = e.id;
		
		$.ajax({
        	method: "POST",
        	url: "getlocationpoints.jsp",
		   	data: {
				bus_id:busId
		   	},
        	success: function (res) {
        		if(res.status==200){
        			let boardingpointoption = document.getElementById("boardingpoint");
        			
        			boardingpointoption.innerHTML="";
        			
        			for(i=0;i<res.boardingpoints.length;i++){
        				let option = document.createElement("option");
        				boardingpointoption.appendChild(option);
        				option.setAttribute("value",`${res.boardingpoints[i].id}`);
        				option.innerHTML=`${res.boardingpoints[i].boardingPoint}`;
        			}
        			
        			let destinationpointoption = document.getElementById("destinationpoint");
        			
        			destinationpointoption.innerHTML="";
        			
        			for(i=0;i<res.droppingpoints.length;i++){
        				let option = document.createElement("option");
        				destinationpointoption.appendChild(option);
        				option.setAttribute("value",`${res.droppingpoints[i].id}`);
        				option.innerHTML=`${res.droppingpoints[i].droppingPoint}`;
        			}
        			
        			let selectedSeats = document.querySelectorAll(".selected");
        			
        			let seats = "";
        		
        			for(i=0;i<selectedSeats.length;i++){
        				seats+=selectedSeats[i].id+",";
        			}
        			
        			if (seats.endsWith(',')) {
        				seats = seats.slice(0, -1);
        			}
        			
        			let bookedseats = document.getElementById("bookedseats");
        			bookedseats.setAttribute("value",seats);
        			
        			let seatPrice = e.dataset.price;
        			
        			let finalPrice = seatPrice*selectedSeats.length;
        			
        			let totalprice = document.getElementById("totalprice");
        			totalprice.setAttribute("value",finalPrice);
        			
        			let busid = document.getElementById("busid2");
        			busid.setAttribute("value",busId);
        			
        			let sendtotalprice = document.getElementById("sendtotalprice");
        			let sendseats = document.getElementById("sendseats");
        			
        			sendtotalprice.setAttribute("value",finalPrice);
        			sendseats.setAttribute("value",seats);
        		}
        		else{
        			alert("Failed To Load!");
        		}
        	}
 		});
	}
	
	function cancelTicket(e){
		let bookingId = e.id;
		
		$.ajax({
        	method: "POST",
        	url: "cancelticket.jsp",
		   	data: {
				booking_id:bookingId
		   	},
        	success: function (res) {
        		const response = JSON.parse(res);
        		
        		if(response.status==200){
        			location.reload();
        		}
        		else{
        			alert("Failed To Cancel");
        		}
        	}
 		});
	}
	
	function changeBusStatus(e){
		let busId = e.id;
		let busStatus = e.value;
		
		$.ajax({
        	method: "POST",
        	url: "changebusstatus.jsp",
		   	data: {
				bus_id:busId,
				bus_status:busStatus
		   	},
        	success: function (res) {
        		const response = JSON.parse(res);
        		
        		let busStatusMessage = document.getElementById("bus-status-message");
        		
        		if(response.status==200){
        			busStatusMessage.innerHTML="Updated Successfully!";
        		}
        		else{
        			busStatusMessage.innerHTML="Failed To Update!";
        		}
        	}
 		});
	}
	
	function changeUserStatus(e){
		let userId = e.id;
		let userStatus = e.value;
		
		$.ajax({
        	method: "POST",
        	url: "changeuserstatus.jsp",
		   	data: {
				user_id:userId,
				user_status:userStatus
		   	},
        	success: function (res) {
        		const response = JSON.parse(res);
        		
        		let userStatusMessage = document.getElementById("user-status-message");
        		
        		if(response.status==200){
        			userStatusMessage.innerHTML="Updated Successfully!";
        		}
        		else{
        			userStatusMessage.innerHTML="Failed To Update!";
        		}
        	}
 		});
	}
	
	function changeBookingStatus(e){
		let bookingId = e.id;
		let bookingStatus = e.value;
		
		$.ajax({
        	method: "POST",
        	url: "changebookingstatus.jsp",
		   	data: {
				booking_id:bookingId,
				booking_status:bookingStatus
		   	},
        	success: function (res) {
        		const response = JSON.parse(res);
        		
        		let bookingStatusMessage = document.getElementById("booking-status-message");
        		
        		if(response.status==200){
        			bookingStatusMessage.innerHTML="Updated Successfully!";
        		}
        		else{
        			bookingStatusMessage.innerHTML="Failed To Update!";
        		}
        	}
 		});
	}
	
	function selectSeat(e){
		let seatId = e.id;
		
		let seat = $(`#${seatId}`);
		
		if(seat.hasClass("selected")){
			seat.attr("class","seat m-5 bg-white");
		}
		else{
			seat.attr("class","seat m-5 bg-green selected");
		}
	}
	
	function showGenderError(){
		alert("Female Gender Is Allowed Only!");
	}
}

let Authentication = new userAuthentication();

function userAuthentication(){
	this.validateUser = validateUser;
	this.sendLoginData = sendLoginData;
	
	function validateUser(){
		let email = document.getElementById("login_email").value;
		let password = document.getElementById("login_password").value;
		
		if(email=="" && password==""){
			let error = document.getElementById("error-message");
			error.innerHTML="Enter a Valid Email and Password!";
			error.setAttribute("style","color:white;text-align:center;");
			return;
		}
		
		$.ajax({
        	method: "POST",
        	url: "postlogin.jsp",
		   	data: {
				email:email,
				password: password
		   	},
        	success: function (res) {
	 			const loginData = JSON.parse(res);
	 			
	 			if(loginData.message=="success"){
	 				sendLoginData(loginData.email,loginData.DBUserType,loginData.DBIsActive);
	 			}
	 			else{
	 				let error = document.getElementById("error-message");
	 				error.innerHTML = "Invalid Username and Credentials";
	 			}
        	}
 		});
	}
	
	function sendLoginData(email,userType,isActive){
		$.ajax({
        	method: "POST",
        	url: "usersession.jsp",
		   	data: {
				email: email,
				user_type: userType,
				is_active: isActive
		   	},
        	success: function (res) {
        		const resData = JSON.parse(res);
        		
        		if(resData.message!="User is Not Active"){
        			if(resData.message=="admin"){
            			location.href="admin.jsp";
            		}
            		else if(resData.message=="user"){
            			location.href="index.jsp";
            		}
        		}
        		else{
        			alert("User is De-Active!");
        		}
        	}
 		});
	}
}