/**
 * 
 */
window.onload = () => {
	
	getInfo();
	
	pendingRequests();
	
	resolvedRequests();
	
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
			
			let nameElement = document.getElementById("reimbursementName");
			nameElement.setAttribute("placeholder", `${info.fname} ${info.lname}`);
			
		}
	};
	xhr.open("GET","http://localhost:8081/ProjectOne/info");
	xhr.send();
}

function pendingRequests(){
	//populating the manager pending requests table
	var xhr2 = new XMLHttpRequest();
	xhr2.onreadystatechange = function (){
		if (xhr2.readyState==4 && xhr2.status==200){
			let info2 = JSON.parse(xhr2.responseText);
			console.log(info2);
			let i=0;
			
			for (i in info2){
				let rid = info2[i].id;
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
				let table = document.getElementById("employee-pending-table");
				let row = document.createElement("tr")
				let col0 = document.createElement("th");
				let col1 = document.createElement("td");
				let col2 = document.createElement("td");
				let col3 = document.createElement("td");
				let col4 = document.createElement("td");
				let col5 = document.createElement("td");
				let col6 = document.createElement("td");
				let linkA = document.createElement("a");
				
				col0.textContent = i;
				col1.textContent = name;
				col2.textContent = reason;
				col3.textContent = `$${amount}`;
				col4.textContent = submit;
				col5.textContent = 'Pending';
				linkA.setAttribute("id", rid);
				linkA.setAttribute("href", "#");
				linkA.textContent = 'Withdraw';
				
				col6.appendChild(linkA);

				row.appendChild(col0);
				row.appendChild(col1);
				row.appendChild(col2);
				row.appendChild(col3);
				row.appendChild(col4);
				row.appendChild(col5);
				row.appendChild(col6);
				
				table.appendChild(row);
				
				document.getElementById(rid).addEventListener("click", function(){
					withdraw(rid);
				});
			}
			
		}
	};
	xhr2.open("GET","http://localhost:8081/ProjectOne/viewMyPendingRequests");
	xhr2.send();
	
}

function resolvedRequests(){
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
				let table = document.getElementById("employee-resolved-table");
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
					col7.textContent = resolve

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
	xhr3.open("GET","http://localhost:8081/ProjectOne/viewMyResolvedRequests");
	xhr3.send();
	
	
}


function withdraw(value) {
	let obj = { id : value }
	console.log(obj);
	let withdrawObj = JSON.stringify(obj);
	var xhr5 = new XMLHttpRequest();
	xhr5.onreadystatechange = function(){
		if (xhr5.readyState==4 && xhr5.status==200){
			let table = document.getElementById("employee-pending-table");
			updateTable(table);
			pendingRequests();
			
			let table2 = document.getElementById("employee-resolved-table");
			updateTable(table2);
			resolvedRequests();
		}
	}
	xhr5.open("POST", "http://localhost:8081/ProjectOne/withdrawRequest");
	xhr5.setRequestHeader("Content-Type", "application/json");
	xhr5.send(withdrawObj);
	
}

function updateTable(table){
	while(table.rows.length > 0){
	       table.deleteRow(0);
	   }
}
