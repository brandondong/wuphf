import React from 'react';
import Navbar from 'react-bootstrap/lib/Navbar';
import NavItem from 'react-bootstrap/lib/NavItem';
import Nav from 'react-bootstrap/lib/Nav';

export default class MainNavbar extends React.Component {
	render() {
		return (
			<Navbar>
				<Navbar.Header>
					<Navbar.Brand>
						<a href="/wuphf">Wuphf</a>
					</Navbar.Brand>
				</Navbar.Header>
					<Nav>
						<NavItem href="/wuphf/integrations.html">Integrations</NavItem>
						<NavItem href="/wuphf/people.html">People</NavItem>
						<NavItem href="/wuphf/messaging.html">Messaging</NavItem>
					</Nav>
			</Navbar>
		);
	}
}