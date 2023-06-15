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
        self.setupUI()
    }
}

extension SignUpViewController {
    private func addSubViews() {
        self.view.addSubview(self.idInputView)
        self.view.addSubview(self.imageSelectButton)
        self.view.addSubview(self.locationAddButton)
        self.view.addSubview(self.navigationBar)
    }
    
    private func setupUI() {
        self.view.backgroundColor = .white
        self.setupImageSelectButtonLayoutConstraint()
        self.setupIDInputViewLayoutConstraint()
        self.setupLoactionAddButtonLayoutConstraint()
        self.setupNavigationBarLayoutConstraint()
        self.makeNavigationBarItem()
        self.setupNavigationBarItem()
        self.setupSheet()
    }
    
    // swiftlint:disable:next function_body_length
    private func setupImageSelectButtonLayoutConstraint() {
        let iamgeButtonTopPadding: CGFloat = 80
        
        self.idInputView.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate(
            [
                self.imageSelectButton.topAnchor.constraint(
                    equalTo: self.navigationBar.bottomAnchor,
                    constant: iamgeButtonTopPadding
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
        if let sheet = sheetPresentationController {
            sheet.detents = [ .large(), .medium() ]
            sheet.selectedDetentIdentifier = .large
            sheet.largestUndimmedDetentIdentifier = .large
        }
    }
    
    private func makeNavigationBarItem() {
        let closeButton = UIBarButtonItem(
            title: "닫기",
            style: .plain,
            target: self,
            action: #selector(self.tappedBackButton)
        )
        let completionButton = UIBarButtonItem(title: "완료", style: .plain, target: self, action: nil)
        self.navigationItem.leftBarButtonItem = closeButton
        self.navigationItem.rightBarButtonItem = completionButton
    }
    
    private func setupNavigationBarItem() {
        self.navigationItem.rightBarButtonItem?.tintColor = ColorPalette.black
        self.navigationItem.leftBarButtonItem?.tintColor = ColorPalette.black
        self.navigationItem.title = "회원가입"
        self.navigationBar.setItems([self.navigationItem], animated: false)
    }
    
    @objc func tappedBackButton() {
        self.dismiss(animated: true)
    }
}
