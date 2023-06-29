//
//  AccountViewController.swift
//  Second-Hand
//
//  Created by PJB on 2023/06/13.
//

import PhotosUI

final class SignUpViewController: UIViewController {
    private let idInputView = IDInputView()
    private let profileImageView = ProfileImageView(frame: .zero)
    private let locationAddButton = LocationAddButton()
    private let navigationBar = UINavigationBar()
    private var itemProviders: [NSItemProvider] = []

    override func viewDidLoad() {
        super.viewDidLoad()
        self.addSubViews()
        self.setupSignUpAppearance()
        self.setupViewConstraint()
        self.setupNavigationBar()
        self.setupSheet()
        self.enableUserInteraction()
        self.setupProfileImageViewTapGesture()
    }
}

extension SignUpViewController: PHPickerViewControllerDelegate {
    func picker(_ picker: PHPickerViewController, didFinishPicking results: [PHPickerResult]) {
        picker.dismiss(animated: true)
        self.itemProviders = results.map {
            $0.itemProvider
        }
        
        if !itemProviders.isEmpty {
            self.fetchImage()
        }
    }
    
    private func presentPicker() {
        var config = PHPickerConfiguration()
        config.filter = .images
        config.selectionLimit = 1
        let imagePicker = PHPickerViewController(configuration: config)
        imagePicker.delegate = self
        self.present(imagePicker, animated: true)
    }
    
    // swiftlint:disable:next function_body_length
    private func fetchImage() {
        guard let itemProvider = itemProviders.first else { return }
        
        if itemProvider.canLoadObject(ofClass: UIImage.self) {
            itemProvider.loadObject(ofClass: UIImage.self) { loadedImage, error in
                guard let image = loadedImage as? UIImage else { return }
                
                DispatchQueue.main.async {
                    self.profileImageView.image = image
                }
                
                if let error {
                    print(error.localizedDescription)
                }
            }
        }
    }
    
    @objc private func didTapImageView(tapGestureReconizer: UITapGestureRecognizer) {
        self.presentPicker()
    }
}

extension SignUpViewController {
    private func setupSignUpAppearance() {
        self.view.backgroundColor = ColorPalette.white
    }
    
    private func addSubViews() {
        self.view.addSubview(self.idInputView)
        self.view.addSubview(self.profileImageView)
        self.view.addSubview(self.locationAddButton)
        self.view.addSubview(self.navigationBar)
    }
    
    private func setupViewConstraint() {
        self.setupProfileImageViewLayoutConstraint()
        self.setupIDInputViewLayoutConstraint()
        self.setupLoactionAddButtonLayoutConstraint()
        self.setupNavigationBarLayoutConstraint()
    }
    
    private func setupNavigationBar() {
        self.setupNavigationBarButtonItem()
        self.setupNavigationBarItemAppearance()
    }
    
    private func enableUserInteraction() {
        self.profileImageView.isUserInteractionEnabled = true
    }
    
    private func setupProfileImageViewTapGesture() {
        let imageViewTapGesture = UITapGestureRecognizer(
            target: self,
            action: #selector(didTapImageView(tapGestureReconizer: ))
        )
        profileImageView.addGestureRecognizer(imageViewTapGesture)
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
    
    @objc private func tappedCloseButton() {
        self.presentingViewController?.dismiss(animated: true)
    }
    
    private func setupProfileImageViewLayoutConstraint() {
        self.profileImageView.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate(
            [
                self.profileImageView.topAnchor.constraint(
                    equalTo: self.navigationBar.bottomAnchor,
                    constant: Constant.Layout.ImageSelectButton.imageButtonTopPadding
                ),
                self.profileImageView.centerXAnchor.constraint(equalTo: self.view.centerXAnchor)
            ]
        )
    }
    
    // swiftlint:disable:next function_body_length
    private func setupIDInputViewLayoutConstraint() {
        self.idInputView.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate(
            [
                self.idInputView.topAnchor.constraint(
                    equalTo: self.profileImageView.bottomAnchor,
                    constant: Constant.Layout.IDInputView.idInputViewTopPadding
                ),
                self.idInputView.leadingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.leadingAnchor),
                self.idInputView.trailingAnchor.constraint(equalTo: self.view.safeAreaLayoutGuide.trailingAnchor),
                self.idInputView.heightAnchor.constraint(equalToConstant: Constant.Layout.IDInputView.idInputViewHeight)
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
        self.locationAddButton.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate(
            [
                self.locationAddButton.topAnchor.constraint(
                    equalTo: self.idInputView.bottomAnchor,
                    constant: Constant.Layout.LocationAddButton.locationAddButtonTopPadding
                ),
                self.locationAddButton.leadingAnchor.constraint(
                    equalTo: self.view.leadingAnchor,
                    constant: Constant.Layout.LocationAddButton.locationAddButtonleadingPadding
                ),
                self.locationAddButton.trailingAnchor.constraint(
                    equalTo: self.view.trailingAnchor,
                    constant: Constant.Layout.LocationAddButton.locationAddButtonTrailingPadding
                )
            ]
        )
    }
}
