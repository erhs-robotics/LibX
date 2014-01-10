/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.erhsroboticsclub.libx.frcx;

import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 *
 * @author david
 */
public class ImageProc {
    
    public static int rmin, rmax, gmin, gmax, bmin, bmax;
    public static int erosionCount = 2;
    public static boolean useConnectivity8 = false;
    public static CriteriaCollection criteriaCollection = new CriteriaCollection();
    public static String fileName = "img.png";
    
    
    public static ParticleAnalysisReport[] getParticles(ColorImage img) throws NIVisionException {
        BinaryImage binaryImage;
        BinaryImage cleanImage;
        BinaryImage filteredImage;
        BinaryImage convexHullImage;
        
        ParticleAnalysisReport particles[] = null;
    
        
        binaryImage = img.thresholdRGB(rmin, rmax, gmin, gmax, bmin, bmax);
        cleanImage = binaryImage.removeSmallObjects(useConnectivity8, erosionCount);
        convexHullImage = cleanImage.convexHull(useConnectivity8);
        filteredImage = convexHullImage.particleFilter(criteriaCollection);
        particles = filteredImage.getOrderedParticleAnalysisReports();
        
        binaryImage.write(fileName);
        
        binaryImage.free();
        cleanImage.free();
        convexHullImage.free();
        filteredImage.free();
        
        return particles;
    }
    
}
