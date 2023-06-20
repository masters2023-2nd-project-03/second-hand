//
//  SelectedImageView.swift
//  Second-Hand
//
//  Created by PJB on 2023/06/19.
//

import UIKit

class ProfileImageView: UIImageView {
    override init(frame: CGRect) {
        super.init(frame: frame)
        setupProfileImageViewUI()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setupProfileImageViewUI()
    }
}

extension ProfileImageView {
    private func setupProfileImageViewUI() {
        self.setupProfileImageViewLayer()
        self.setupProfileImageViewLayoutConstraint()
        self.setupProfileImageView()
        self.setupProfileImageViewAppearance()
    }
    
    private func setupProfileImageViewLayer() {
        self.layer.borderWidth = Constant.Layout.borderWidth
        self.layer.borderColor = ColorPalette.gray500?.cgColor
        self.layer.cornerRadius = Constant.Layout.diameter / 2
        self.clipsToBounds = true
    }
    
    private func setupProfileImageViewAppearance() {
        self.tintColor = ColorPalette.black
    }
    
    private func setupProfileImageView() {
        self.image = Constant.ImageAsset.camera
        self.contentMode = .scaleAspectFill
    }
    
    private func setupProfileImageViewLayoutConstraint() {
        NSLayoutConstraint.activate(
            [
                self.widthAnchor.constraint(equalToConstant: Constant.Layout.diameter),
                self.heightAnchor.constraint(equalToConstant: Constant.Layout.diameter)
            ]
        )
    }
}
