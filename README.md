# aspe-sp

ASPE is a project mainly for learning and demonstration.
It's the web portal of a fictional company.

This is the backend part of the project: A REST API written in Spring Boot (Java).

The [Frontend Part](https://github.com/truj/aspe-ng) is written in Angular 11.


## Techniques

<ul>
	<li>Frontend</li>
	<ul>
		<li>Angular 11</li>
		<li>MDBootstrap (for the moment...)</li>
	</ul>
	<li>Backend</li>
	<ul>
		<li>Spring Boot (Java)</li>
		<li>MariaDB</li>
		<li>JPA / Hibernate (customer administration)</li>
		<li>JDBC (user administration)</li>
		<li>Elasticsearch / JPA (product administration)</li>
		<li>Elasticsearch / REST Client (invoice administration)</li>
	</ul>
</ul>

## Roadmap for Features

<ul>
	<li>Initializer</li>
	<ul>
		<li>Checking Server States</li>
		<ul>
			<li>🟩Implemented🟩 Backend API (REST)</li>
			<li>🟩Implemented🟩 Elasticsearch (REST API)</li>
			<li>🟩Implemented🟩 Elasticsearch Head Plugin</li>
		</ul>
		<li>Database Tables</li>
		<ul>
			<li>Customers</li>
			<ul>
				<li>🟩Implemented🟩 Show the number of customers</li>
				<li>🟩Implemented🟩 Initialize records: Adds 10 fake customers at a time.</li>
			</ul>
			<li>Users</li>
			<ul>
				<li>🟩Implemented🟩 Show the number of users</li>
				<li>🟩Implemented🟩 Initialize records: Adds 7 fake users at a time.</li>
			</ul>
		</ul>
		<li>Elasticsearch</li>
		<ul>
			<li>Templates</li>
			<ul>
				<li>🟥Still missing🟥 Checking Templates</li>
				<li>🟥Still missing🟥 Creating or updating Templates if they don't exist or their Mappings or Settings or Index patterns are outdated.</li>
			</ul>
			<li>Indices</li>
			<ul>
				<li>🟥Still missing🟥 Checking Indices</li>
				<li>🟥Still missing🟥 Creating or updating Indices, if they don't exist or their Mappings or Settings are outdated.</li>
			</ul>
			<li>Products</li>
			<ul>
				<li>🟥Still missing🟥 Show the number of products</li>
				<li>🟥Still missing🟥 Initialize documents: Add several fake products at a time</li>
			</ul>
			<li>Invoices</li>
			<ul>
				<li>🟥Still missing🟥 Show the number of invoices</li>
				<li>🟥Still missing🟥 Initialize documents: Add several fake invoices at a time</li>
			</ul>
		</ul>
	</ul>
	<li>Customers (JPA / Hibernate)</li>
	<ul>
		<li>Customer Search</li>
		<ul>
			<li>🟩Implemented🟩 Customer search using a custom filter</li>
			<li>🟥Still missing🟥 Pagination of the result list</li>
		</ul>
		<li>Customer manipulation</li>
		<ul>
			<li>🟩Implemented🟩 Customer creation</li>
			<li>🟩Implemented🟩 Customer update</li>
			<li>🟥Still missing🟥 Frontend Validation</li>
			<li>🟧Partly implemented🟧 Backend Validation</li>
		</ul>
	</ul>
	<li>Users (JDBC)</li>
	<ul>
		<li>User Search</li>
		<ul>
			<li>🟩Implemented🟩 User search using a custom filter</li>
			<li>🟥Still missing🟥 Pagination of the result list</li>
		</ul>
		<li>User manipulation</li>
		<ul>
			<li>🟩Implemented🟩 User creation</li>
			<li>🟩Implemented🟩 User update</li>
			<li>🟥Still missing🟥 Frontend Validation</li>
			<li>🟥Still missing🟥 Backend Validation</li>
		</ul>
	</ul>
	<li>Products (Elasticsearch / JPA)</li>
	<ul>
		<li>Product Search</li>
		<ul>
			<li>🟥Still missing🟥 Product search using a custom filter</li>
			<li>🟥Still missing🟥 Pagination of the result list</li>
		</ul>
		<li>Product manipulation</li>
		<ul>
			<li>🟥Still missing🟥 Product creation</li>
			<li>🟥Still missing🟥 Product update</li>
			<li>🟥Still missing🟥 Frontend Validation</li>
			<li>🟥Still missing🟥 Backend Validation</li>
		</ul>
	</ul>
	<li>Invoices (Elasticsearch / REST Client)</li>
	<ul>
		<li>Invoice Search</li>
		<ul>
			<li>🟥Still missing🟥 Invoice search using a custom filter</li>
			<li>🟥Still missing🟥 Pagination of the result list</li>
		</ul>
		<li>Product manipulation</li>
		<ul>
			<li>🟥Still missing🟥 Invoice creation</li>
			<li>🟥Still missing🟥 Invoice update</li>
			<li>🟥Still missing🟥 Frontend Validation</li>
			<li>🟥Still missing🟥 Backend Validation</li>
		</ul>
	</ul>
	<li>Frontend Tests</li>
	<ul>
		<li>🟥Still missing🟥 Write tests for everything</li>
	</ul>
	<li>Backend Tests</li>
	<ul>
		<li>🟥Still missing🟥 Write tests for everything</li>
	</ul>
	<li>SCSS Styling</li>
	<ul>
		<li>🟥Still missing🟥 Choosing the final SCSS framework to use or build my own</li>
		<li>🟥Still missing🟥 Apply stylings</li>
	</ul>
	<li>Security Features</li>
	<ul>
		<li>🟥Still missing🟥 User Login (OAuth)</li>
		<li>🟥Still missing🟥 Access Restriction for some parts</li>
		<li>🟥Still missing🟥 Password change</li>
		<ul>
			<li>🟥Still missing🟥 Initially or after resetting</li>
			<li>🟥Still missing🟥 With a valid password</li>
		</ul>
	</ul>
</ul>
