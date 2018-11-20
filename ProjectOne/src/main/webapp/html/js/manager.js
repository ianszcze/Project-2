/**
 * 
 */
window.onload = () => {
	
	getInfo();
	
	allPendingRequests();
	
	allResolvedRequests();
	
	allEmployees();
	
}

function getInfo(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function (){
		if (xhr.readyState==4 && xhr.status==200){
			let info = JSON.parse(xhr.responseText);
			
			let fullname = document.getElementById("name");
			let username = document.getElementById("username");
			let firstname = document.getElementById("firstname");
			let lastname = document.getElementById("lastname");
			let email = document.getElementById("email");
			let password = document.getElementById("password");
			
			fullname.innerHTML = `Welcome ${info.fname} ${info.lname}!`;
			username.innerHTML = `Username: ${info.username}`;
			firstname.innerHTML = `First Name: ${info.fname}`;
			lastname.innerHTML = `Last Name: ${info.lname}`;
			email.innerHTML = `Email: ${info.email}`;
		}
	};
	xhr.open("GET","http://localhost:8081/ProjectOne/info");
	xhr.send();
}

function allPendingRequests(){
	//populating the manager pending requests table
	var xhr2 = new XMLHttpRequest();
	xhr2.onreadystatechange = function (){
		if (xhr2.readyState==4 && xhr2.status==200){
			let info2 = JSON.parse(xhr2.responseText);
			let i=0;
			
			for (i in info2){
				let rId = info2[i].id;
				let employeeid = info2[i].eId;
				let managerid = info2[i].mId;
				let status = info2[i].reimburseReq;
				let reason = info2[i].reimburseFor;
				let amount = info2[i].reimburseAmt;
				let submit = info2[i].submitTime;
				let resolve = info2[i].resolveTime;
				let name = info2[i].name;
				
				i++;
				
				//name reason submitted amount status
				let table = document.getElementById("manager-pending-table");
				let row = document.createElement("tr")
				let col0 = document.createElement("th");
				let col1 = document.createElement("td");
				let col2 = document.createElement("td");
				let col3 = document.createElement("td");
				let col4 = document.createElement("td");
				let col5 = document.createElement("td");
				let linkA = document.createElement("a");
				let col6 = document.createElement("td");
				let linkD = document.createElement("a");

				
				col0.textContent = i;
				col1.textContent = name;
				col2.textContent = reason;
				col3.textContent = `$${amount}`;
				col4.textContent = submit;
				linkA.textContent = 'Approve';
				linkD.textContent = 'Deny';
				
				linkA.setAttribute("id", `a${rId}`);
				linkA.setAttribute("href", "#");
				
				linkD.setAttribute("id", `d${rId}`);
				linkD.setAttribute("href", "#");
				
				col5.appendChild(linkA);
				col6.appendChild(linkD);

				row.appendChild(col0);
				row.appendChild(col1);
				row.appendChild(col2);
				row.appendChild(col3);
				row.appendChild(col4);
				row.appendChild(col5);
				row.appendChild(col6);
				
				table.appendChild(row);
				
				document.getElementById(`a${rId}`).addEventListener("click", function(){ approve(rId)});
				document.getElementById(`d${rId}`).addEventListener("click", function(){ deny(rId)});
				
				
			}
			
		}
	};
	xhr2.open("GET","http://localhost:8081/ProjectOne/viewAllPendingRequests");
	xhr2.send();
}

function allResolvedRequests(){
	//populating the manager resolved requests table
	var xhr3 = new XMLHttpRequest();
	xhr3.onreadystatechange = function (){
		if (xhr3.readyState==4 && xhr3.status==200){
			let info3 = JSON.parse(xhr3.responseText);
			let j=0;
			
			for (j in info3){
				let id = info3[j].id;
				let employeeid = info3[j].eId;
				let managerid = info3[j].mId;
				let status = info3[j].reimburseReq;
				let reason = info3[j].reimburseFor;
				let amount = info3[j].reimburseAmt;
				let submit = info3[j].submitTime;
				let resolve = info3[j].resolveTime;
				let name = info3[j].name;
				let mName = info3[j].mName;
				
				j++;
				//# name reason amount datesubmitted status manager dateresolved
				let table = document.getElementById("manager-resolved-table");
				let row = document.createElement("tr")
				let col0 = document.createElement("th");
				let col1 = document.createElement("td");
				let col2 = document.createElement("td");
				let col3 = document.createElement("td");
				let col4 = document.createElement("td");
				let col5 = document.createElement("td");
				let col6 = document.createElement("td");
				let col7 = document.createElement("td");
				
				col0.textContent = j;
				col1.textContent = name;
				col2.textContent = reason;
				col3.textContent = `$${amount}`;
				col4.textContent = submit;
				col5.textContent = status;
				if (status == "Withdrawn"){
					col6.setAttribute("class", "text-center");
					col6.textContent = "-";
				}
				else {
					col6.textContent = mName;
				}
				col7.textContent = resolve;

				row.appendChild(col0);
				row.appendChild(col1);
				row.appendChild(col2);
				row.appendChild(col3);
				row.appendChild(col4);
				row.appendChild(col5);
				row.appendChild(col6);
				row.appendChild(col7);
				
				table.appendChild(row);
				
			}
			
		}
	};
	xhr3.open("GET","http://localhost:8081/ProjectOne/viewAllResolvedRequests");
	xhr3.send();
}

function allEmployees() {

	//populating the manager employees table
	var xhr4 = new XMLHttpRequest();
	xhr4.onreadystatechange = function (){
		if (xhr4.readyState==4 && xhr4.status==200){
			let info4 = JSON.parse(xhr4.responseText);
			console.log(info4);
			let k=0;
			
			for (k in info4){
				let eId = info4[k].id;
				let firstname = info4[k].fname;
				let lastname=info4[k].lname;
				let username=info4[k].username;
				let password=info4[k].password;
				let email=info4[k].email;
				let type=info4[k].type;
				
				let fullname = `${firstname} ${lastname}`;
				
				k++;
				//# name username password email
				let table = document.getElementById("manager-employees-table");
				let row = document.createElement("tr")
				let col0 = document.createElement("th");
				let col1 = document.createElement("td");
				let col2 = document.createElement("td");
				let col3 = document.createElement("td");
				let col4 = document.createElement("td");
				let col5 = document.createElement("td");
				let linkV = document.createElement("a");
				
				col0.textContent = k;
				col1.textContent = fullname;
				col2.textContent = username;
				col3.textContent = email;
				col4.textContent = type;
				linkV.setAttribute("data-toggle", "modal");
				linkV.setAttribute("data-target", "#viewEmployee");
				linkV.setAttribute("href", "#");
				linkV.setAttribute("class", 'employeeView');
				linkV.setAttribute("id", eId);
				linkV.textContent = 'View';
				
				col5.appendChild(linkV);
				
				row.appendChild(col0);
				row.appendChild(col1);
				row.appendChild(col2);
				row.appendChild(col3);
				row.appendChild(col4);
				row.appendChild(col5);
				
				table.appendChild(row);
				
				document.getElementById(eId).addEventListener("click", function() { 
					let table = document.getElementById("manager-employee-table");
					updateTable(table);
					employeePendingRequests(eId, fullname)});
			}
			
		}
	};
	xhr4.open("GET","http://localhost:8081/ProjectOne/viewAllEmployees");
	xhr4.send();
}

function employeePendingRequests(eId, fullname){
	let obj = { id : eId }
	let pendingObj = JSON.stringify(obj);
	//populating the manager pending requests table
	var xhr7 = new XMLHttpRequest();
	xhr7.onreadystatechange = function (){
		if (xhr7.readyState==4 && xhr7.status==200){
			let info2 = JSON.parse(xhr7.responseText);
			let i=0;
			let header = document.getElementById("employeeReimbursements");
			header.innerHTML = `${fullname}'s Reimbursements`;
			
			for (i in info2){
				
				console.log(info2);
				let rId = info2[i].id;
				let employeeid = info2[i].eId;
				let managerid = info2[i].mId;
				let status = info2[i].reimburseReq;
				let reason = info2[i].reimburseFor;
				let amount = info2[i].reimburseAmt;
				let submit = info2[i].submitTime;
				let resolve = info2[i].resolveTime;
				let name = info2[i].name;
				let mName = info2[i].mName;
				
				console.log(name);
				i++;
				
				//name reason submitted amount status
				let table = document.getElementById("manager-employee-table");
				let row = document.createElement("tr")
				let col0 = document.createElement("th");
				let col1 = document.createElement("td");
				let col2 = document.createElement("td");
				let col3 = document.createElement("td");
				let col4 = document.createElement("td");
				let col5 = document.createElement("td");
				let col6 = document.createElement("td");
				
				
				
				col0.textContent = i;
				col1.textContent = reason;
				col2.textContent = `$${amount}`;
				col3.textContent = status;
				col4.textContent = submit;
				if (mName == "null null"){
					col5.setAttribute("class", "text-center");
					col5.textContent = "-";
				} else {
					col5.textContent = mName;	
				}
				if (resolve == null){
					col6.setAttribute("class", "text-center");
					col6.textContent = "-";
				}
				else {					
					col6.textContent = resolve;
				}

				row.appendChild(col0);
				row.appendChild(col1);
				row.appendChild(col2);
				row.appendChild(col3);
				row.appendChild(col4);
				row.appendChild(col5);
				row.appendChild(col6);
				
				table.appendChild(row);
			}
			
		}
	};
	xhr7.open("POST","http://localhost:8081/ProjectOne/employeePendingRequests");
	xhr7.send(pendingObj);
}

function approve(value) {
	let obj = { id : value }
	let approveObj = JSON.stringify(obj);
	var xhr5 = new XMLHttpRequest();
	xhr5.onreadystatechange = function(){
		if (xhr5.readyState==4 && xhr5.status==200){
			let table = document.getElementById("manager-pending-table");
			updateTable(table);
			allPendingRequests();
			
			let table2 = document.getElementById("manager-resolved-table");
			updateTable(table2);
			allResolvedRequests();
		}
	}
	xhr5.open("POST", "http://localhost:8081/ProjectOne/approveRequest");
	xhr5.setRequestHeader("Content-Type", "application/json");
	xhr5.send(approveObj);
}

function deny(value) {
	let obj2 = { id : value }
	let denyObj = JSON.stringify(obj2);
	var xhr6 = new XMLHttpRequest();
	xhr6.onreadystatechange = function(){
		if (xhr6.readyState==4 && xhr6.status==200){
			let table = document.getElementById("manager-pending-table");
			updateTable(table);
			allPendingRequests();
			
			let table2 = document.getElementById("manager-resolved-table");
			updateTable(table2);
			allResolvedRequests();
		}
	}
	xhr6.open("POST", "http://localhost:8081/ProjectOne/denyRequest");
	xhr6.setRequestHeader("Content-Type", "application/json");
	xhr6.send(denyObj);
	
}

function updateTable(table){
	while(table.rows.length > 0){
	       table.deleteRow(0);
	   }
}
