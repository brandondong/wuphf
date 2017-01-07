import React from 'react';
import Navbar from 'react-bootstrap/lib/Navbar';
import NavItem from 'react-bootstrap/lib/NavItem';
import Jumbotron from 'react-bootstrap/lib/Jumbotron';
import Nav from 'react-bootstrap/lib/Nav';

export default class MainNavbar extends React.Component {
	render() {
		return (
			<Navbar>
				<Navbar.Header>
					<Navbar.Brand>
						<a href="index.html">Wuphf</a>
					</Navbar.Brand>
				</Navbar.Header>
					<Nav>
						<NavItem href="integrations.html">Integrations</NavItem>
						<NavItem href="people.html">People</NavItem>
						<NavItem href="messaging.html">Messaging</NavItem>
					</Nav>
			</Navbar>
		);
	}
}

export class MainJumbotron extends React.Component {
	render() {
		return (
			<Jumbotron>
				<div className="container">
					<h1>{this.props.title}</h1>
					<p>{this.props.message}</p>
				</div>
			</Jumbotron>
		);
	}
}