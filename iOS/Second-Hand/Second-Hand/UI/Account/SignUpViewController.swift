//
//  AccountViewController.swift
//  Second-Hand
//
//  Created by PJB on 2023/06/13.
//

import UIKit

final class SignUpViewController: UIViewController {
    private let idInputView = IDInputView()
    private let imageSelectButton = ImageSelectButton()
    private let locationAddButton = LocationAddButton()
    private let navigationBar = UINavigationBar()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.addSubViews()
        self.setupSignUpAppearance()
        self.setupViewConstraint()
        self.setupNavigationBar()
        self.setupSheet()
    }
}

extension SignUpViewController {
    private func addSubViews() {
        self.view.addSubview(self.idInputView)
        self.view.addSubview(self.imageSelectButton)
        self.view.addSubview(self.locationAddButton)
        self.view.addSubview(self.navigationBar)
    }
    
    private func setupViewConstraint() {
        self.setupImageSelectButtonLayoutConstraint()
        self.setupIDInputViewLayoutConstraint()
        self.setupLoactionAddButtonLayoutConstraint()
        self.setupNavigationBarLayoutConstraint()
    }
    
    private func setupSignUpAppearance() {
        self.view.backgroundColor = ColorPalette.white
    }
    
    private func setupNavigationBar() {
        self.setupNavigationBarButtonItem()
        self.setupNavigationBarItemAppearance()
    }
    
    // swiftlint:disable:next function_body_length
    private func setupImageSelectButtonLayoutConstraint() {
        let imageButtonTopPadding: CGFloat = 80
        
        self.idInputView.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate(
            [
                self.imageSelectButton.topAnchor.constraint(
                    equalTo: self.navigationBar.bottomAnchor,
                    constant: imageButtonTopPadding
                ),
                self.imageSelectButton.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
            ]
        )
    }
    
    // swiftlint:disable:next function_body_length
    private func setupIDInputViewLayoutConstraint() {
        let idInputViewHeight: CGFloat = 44
        let idInputViewTopPadding: CGFloat = 24
        
        self.imageSelectButton.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate(
            [
                self.idInputView.topAnchor.constraint(
                    equalTo: self.imageSelectButton.bottomAnchor,
                    constant: idInputViewTopPadding
                ),
                self.idInputView.leadingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.leadingAnchor),
                self.idInputView.trailingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.trailingAnchor),
                self.idInputView.heightAnchor.constraint(equalToConstant: idInputViewHeight)
            ]
        )
    }
    
    private func setupNavigationBarLayoutConstraint() {
        self.navigationBar.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate(
            [
                self.navigationBar.topAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.topAnchor),
                self.navigationBar.leadingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.leadingAnchor),
                self.navigationBar.trailingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.trailingAnchor)
            ]
        )
    }
    // swiftlint:disable:next function_body_length
    private func setupLoactionAddButtonLayoutConstraint() {
        let locationAddButtonTopPadding: CGFloat = 51
        let locationAddButtonleadingPadding: CGFloat = 16
        let locationAddButtonTrailingPadding: CGFloat = -16
        
        self.locationAddButton.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate(
            [
                self.locationAddButton.topAnchor.constraint(
                    equalTo: self.idInputView.bottomAnchor,
                    constant: locationAddButtonTopPadding
                ),
                self.locationAddButton.leadingAnchor.constraint(
                    equalTo: self.view.leadingAnchor,
                    constant: locationAddButtonleadingPadding
                ),
                self.locationAddButton.trailingAnchor.constraint(
                    equalTo: self.view.trailingAnchor,
                    constant: locationAddButtonTrailingPadding
                )
            ]
        )
    }
    
    private func setupSheet() {
        guard let sheet = sheetPresentationController else {
            return
        }
        
        sheet.detents = [ .large(), .medium() ]
        sheet.selectedDetentIdentifier = .large
        sheet.largestUndimmedDetentIdentifier = .large
    }
    
    private func setupNavigationBarButtonItem() {
        let closeButton = self.makeBarButtonItem(
            title: Constant.StringLiteral.NavigationItem.closeButton,
            action: #selector(self.tappedCloseButton)
        )
        let completionButton = self.makeBarButtonItem(title: Constant.StringLiteral.NavigationItem.completionButton)
        
        self.navigationItem.leftBarButtonItem = closeButton
        self.navigationItem.rightBarButtonItem = completionButton
    }
    
    private func setupNavigationBarItemAppearance() {
        self.navigationItem.rightBarButtonItem?.tintColor = ColorPalette.gray800
        self.navigationItem.leftBarButtonItem?.tintColor = ColorPalette.gray900
        self.navigationItem.title = Constant.StringLiteral.NavigationItem.title
        self.navigationBar.setItems([self.navigationItem], animated: false)
    }
    
    @objc func tappedCloseButton() {
        self.dismiss(animated: true)
    }
    
    private func makeBarButtonItem(
        title: String,
        style: UIBarButtonItem.Style = .plain,
        action: Selector? = nil
    ) -> UIBarButtonItem {
        
        return UIBarButtonItem(
            title: title,
            style: style,
            target: self,
            action: action
        )
    }
}
